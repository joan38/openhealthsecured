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

import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;

import sancane_test.INT_U32;
import sancane_test.TYPE;

import es.libresoft.mdnf.FloatType;



public class testFloatMDNF {

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
	  
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//FloatType ft = new FloatType(-3,32000);
		//System.out.println("Creado: " + ft + ", valor =" + ft.doubleValue());
		//FloatType.getFloatType(0x80700000);
		//FloatType ft = new FloatType(0x7B080000);
		//FloatType ft = new FloatType(223483647);
		//FloatType ft = new FloatType(2147483647L);
		//FloatType ft = new FloatType(223483647L);
		FloatType ft = new FloatType(-8,-40);
		System.out.println("Creado: " + ft + ", valor =" + ft.doubleValueRepresentation());
		System.out.println("exponente: " + ft.getExponent() + ", magnitude: " + ft.getMagnitude() + ", raw: " + ft.getRawRepresentation());
		System.out.println("Long Value: " + ft.longValue());
		System.out.println("Exponente: " + ft.doubleValueRepresentation());
		INT_U32 iu = new INT_U32(new Long(ft.longValue()));
		try {		
			IEncoder<INT_U32> encoder = CoderFactory.getInstance().newEncoder("MDER");
			IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
			
			System.out.println("Conectandos con el servidor...");
			Socket s = new Socket("127.0.0.1",9999);
			//Socket s = new Socket("192.168.0.14",9999);
			encoder.encode(iu, s.getOutputStream());
			System.out.println("BEEP");
			s.close();
			// Decoding the specified input stream
		}catch (Exception e){
			System.out.println("Petardazo!");
			e.printStackTrace();
		}
	}

}
