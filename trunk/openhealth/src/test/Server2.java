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

import ieee_11073.part_20601.asn1.*;

import java.net.ServerSocket;
import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;

public class Server2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IEncoder<NomPartition> encoder = CoderFactory.getInstance().newEncoder("PER");
			IDecoder decoder = CoderFactory.getInstance().newDecoder("PER");
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server arrancado en " + ss.getInetAddress() + ":" + ss.getLocalPort());
			while (true){
				System.out.println("Esperando conexiones de clientes...");
				Socket s = ss.accept();
				System.out.println("Recivida petición...");
				NomPartition np = decoder.decode(s.getInputStream(), NomPartition.class);
				System.out.println("Recivido " + np.getValue());
				s.close();
			}
			// Decoding the specified input stream
			}catch (Exception e){
				System.out.println("Petardazo!");
				e.printStackTrace();
			}

	}

}
