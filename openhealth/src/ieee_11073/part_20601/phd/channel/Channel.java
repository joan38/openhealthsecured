/*
Copyright (C) 2008-2009  Santiago Carot Nemesio
email: scarot@libresoft.es

This program is a (FLOS) free libre and open source implementation
of a multiplatform manager device written in java according to the
ISO/IEEE 11073-20601. Manager application is designed to work in 
DalvikVM over android platform.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
package ieee_11073.part_20601.phd.channel;

import ieee_11073.part_20601.asn1.ApduType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;

import es.libresoft.openhealth.Device;
import es.libresoft.openhealth.events.Event;
import es.libresoft.openhealth.events.EventType;
import es.libresoft.openhealth.utils.IFIFO;

public class Channel {
	private int id;
	private InputStream input;
	private OutputStream output;
	
	private IDecoder decoder;
	private IEncoder<ApduType> encoder;
	
	private IFIFO<ApduType> inputQueue;
	private ChannelEventHandler eventHandler;
	
	private ReceiverThread receiver;
	
	private boolean initialized = false;
	
	private Semaphore repeatSem = new Semaphore(1);
	private boolean repeat = true;
	
	public Channel (InputStream input, OutputStream output) throws Exception {
		this.input = input;
		this.output = output;		
		//Set default encoding rules to MDER
		decoder = CoderFactory.getInstance().newDecoder(Device.MDER_DEFUALT);
		encoder = CoderFactory.getInstance().newEncoder(Device.MDER_DEFUALT);
	}	
	
	
	
	public synchronized void configureChannel (int id, IFIFO<ApduType> inputQueue, ChannelEventHandler eventHandler) throws InitializedException {
		if (initialized)
			throw new InitializedException ("Channel is already initialized");
		
		this.id = id;
		this.eventHandler = eventHandler;
		this.inputQueue = inputQueue;
		
		receiver = new ReceiverThread();
		receiver.start();
		
		initialized = true;
	}
	
	public synchronized void sendAPDU (ApduType apdu) throws Exception {
		if (!initialized)
			throw new InitializedException ("Channel is not initialized");
		encoder.encode(apdu, output);
	}
	

	public void setReceiverStatus (boolean status) {
		try {
			repeatSem.acquire();
			this.repeat = status;
			if (!this.repeat && receiver.isInterrupted()) {
				receiver.interrupt();
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted receiver (" + id + ")");
		} finally {
			repeatSem.release();
		}
	}
	
	private boolean shouldRepeat () {
		boolean r = false;
		try {
			repeatSem.acquire();
			r = this.repeat;
		} catch (InterruptedException e) {
			System.out.println("Interrupted receiver (" + id + ")");
		} finally {
			repeatSem.release();
		}
		return r;
	}
	/**
	 * Receiver thread for the input channel
	 * @author sancane
	 */
	
	public class ReceiverThread extends Thread {
		public void run() {
			while(shouldRepeat ()){
		 		try {
		 			inputQueue.add(decoder.decode(input, ApduType.class));
		 		}catch (InterruptedException e) {
					System.err.println("Interrupted receiver (" + id + ")");
		 		}catch (NullPointerException e) {
		 			//An APDUType is not received Ignore
		 			System.err.println("APDUType is not received");;
				}catch (Exception e) {
					//EOF readed because channel is closed
					eventHandler.processEvent(new Event(EventType.IND_TRANS_DESC));
				}
			}
			System.out.println("Receiver thread exiting (" + id + ")...");
		}
	}
}
