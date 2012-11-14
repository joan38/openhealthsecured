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

import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;

import sancane_test.INT_U16;
import sancane_test.INT_U32;
import es.libresoft.mdnf.FloatType;
import es.libresoft.mdnf.SFloatType;

public class testSFloatType {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SFloatType ft = new SFloatType((short)-7,(short)-5);
		System.out.println("Creado: " + ft + ", valor =" + ft.doubleValueRepresentation());
		System.out.println("exponente: " + ft.getExponent() + ", magnitude: " + ft.getMagnitude() + ", raw: " + ft.getRawRepresentation());
		System.out.println("Long Value: " + ft.doubleValueRepresentation());
		INT_U16 iu = new INT_U16(new Integer(ft.intValue()));
		try {		
			IEncoder<INT_U16> encoder = CoderFactory.getInstance().newEncoder("MDER");
			IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
			
			System.out.println("Conectando con el servidor...");
			Socket s = new Socket("127.0.0.1",9999);
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
