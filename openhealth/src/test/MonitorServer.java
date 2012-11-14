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

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class MonitorServer {

	static final byte[] HEX_CHAR_TABLE = {
	    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
	    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
	    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
	    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
	  }; 

	  public static String getHexString(byte[] raw) 
	    throws UnsupportedEncodingException 
	  {
	    byte[] hex = new byte[2 * raw.length];
	    int index = 0;

	    for (byte b : raw) {
	      int v = b & 0xFF;
	      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
	      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
	    }
	    return new String(hex, "ASCII");
	  }
	  /*
	  public static void main(String args[]) throws Exception{
	    byte[] byteArray = {
	      (byte)255, (byte)254, (byte)253, 
	      (byte)252, (byte)251, (byte)250
	    };
	    System.out.println(getHexString(byteArray));
	  }
	  */
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		try{
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server arrancado en " + ss.getInetAddress() + ":" + ss.getLocalPort());
			for(;;){
				System.out.println("Esperando conexiones de clientes...");
				Socket s = ss.accept();
				System.out.println("Recivida petición...");
				InputStream is = s.getInputStream();
				int i,index=1;
				byte[] hex = new byte[1];				
				while ((i=is.read())!=-1){
					hex[0]=(byte)i;
					System.out.println("*" + (index++) + ": " + getHexString(hex));
				}
				is.close();
				s.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
