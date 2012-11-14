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
package test;

import ieee_11073.part_20601.asn1.INT_U16;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;

import sancane_test.NomPartition;

public class TestChannelClient {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1",9999);
			SenderThread st = new SenderThread(s.getOutputStream());
			ReceiverThread rt = new ReceiverThread(s.getInputStream());
			st.start();
			rt.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class SenderThread extends Thread {
		OutputStream output;
		public SenderThread (OutputStream out){
			output = out;
		}
		public void run() {
			try {
				IEncoder<INT_U16> encoder = CoderFactory.getInstance().newEncoder("MDER");
				
				INT_U16 iu;
				for (int i=0; i <= 1; i++){
					iu = new INT_U16(new Integer(i));
					encoder.encode(iu, output);
					//System.out.println("-->" + i);
				}
				System.out.println("fin");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static class ReceiverThread extends Thread {
		InputStream input;
		public ReceiverThread (InputStream in){
			input = in;
		}
		public void run() {
			try {
				IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
				int i=0;
				for(;;){	
					INT_U16 ui = decoder.decode(input, INT_U16.class);
					if (ui.getValue().intValue() == i) {
						System.out.println("Recibido: " + ui.getValue());
						i++;
					}else throw new Exception("ERRRRRRRROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
				}
			} catch (Exception e) {
				System.out.println("No se han recibido todos los paquetes en orden");
			}
		}
	}
}
