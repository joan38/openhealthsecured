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

import ieee_11073.part_20601.phd.channel.Channel;
import ieee_11073.part_20601.phd.channel.VirtualChannel;

import java.net.ServerSocket;
import java.net.Socket;

import es.libresoft.openhealth.Agent;
import es.libresoft.openhealth.chap.ManagerAuthenticator;

public class TestChannels {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server arrancado en " + ss.getInetAddress() + ":" + ss.getLocalPort());
			for(;;){
				Socket s = ss.accept();
				Channel chnl = new Channel (s.getInputStream(),s.getOutputStream());
				VirtualChannel vch = new VirtualChannel(chnl);
				Agent a = new Agent(new ManagerAuthenticator());
				a.setVirtualChannel(vch);
			}
		}catch (Exception e){
			System.out.println("Petardazo!");
			e.printStackTrace();
		}
	}
}
