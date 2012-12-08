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
package es.libresoft.openhealth;

import es.libresoft.openhealth.chap.ManagerAuthenticator;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import ieee_11073.part_20601.phd.channel.Channel;
import ieee_11073.part_20601.phd.channel.VirtualChannel;
import java.io.IOException;
import java.net.ServerSocket;

public class SerialChannel extends Thread {

    private boolean finish = false;
    private String portName = "COM1";
    int speed = 115200;
    private SerialPort serialPort;
    private Mux mux;
    private DeMux demux;
    private ServerSocket ss;
    private final ManagerAuthenticator authenticator = new ManagerAuthenticator();

    @SuppressWarnings("restriction")
    public void run() {
        String status = "";

        //begin
        CommPortIdentifier portIdentifier;
        boolean conn = false;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                finish = true;
            } else {
                serialPort = (SerialPort) portIdentifier.open("RTBug_network", 2000);
                serialPort.setSerialPortParams(speed, SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                demux = new DeMux(serialPort.getInputStream());
                mux = new Mux(serialPort.getOutputStream());
                conn = true;
            }
        } catch (NoSuchPortException e) {
            System.out.println("the connection could not be made");
            e.printStackTrace();
        } catch (PortInUseException e) {
            System.out.println("the connection could not be made");
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            System.out.println("the connection could not be made");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("the connection could not be made");
            e.printStackTrace();
        }
        try {

            System.out.println("Waiting for clients...");
            Channel chnl = new Channel(demux, mux);
            VirtualChannel vch = new VirtualChannel(chnl);
            Agent a = new Agent(authenticator);
            a.setVirtualChannel(vch);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    public void finish() {
        // TODO Auto-generated method stub
        this.finish = true;
        System.out.println("Closing manager service...");
        try {
            ss.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}