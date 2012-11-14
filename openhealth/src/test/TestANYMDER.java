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


//import ieee_11073_20601_asn1.*;
import sancane_test.*;

import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;



public class TestANYMDER {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] byteArray = {(byte)255, (byte)128, (byte)255, (byte)128};
		byte[] byteArray2 = {};
		AVAX_Type ava = new AVAX_Type();
		OID_Type oit = new OID_Type();
		oit.setValue(new INT_U16(new Integer(9)));
		ava.setAttribute_id(oit);
		ava.setAttribute_value(byteArray2);
		
		//EnumVal iu = new EnumVal();
		//iu.selectEnum_text_string(byteArray);
		//iu.selectEnum_obj_id(oit);
		//iu.selectEnum_bit_str(new BitString(byteArray));
		
		try {		
			IEncoder<AVAX_Type> encoder = CoderFactory.getInstance().newEncoder("MDER");
			IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
			
			System.out.println("Conectando con el servidor...");
			Socket s = new Socket("127.0.0.1",9999);
			encoder.encode(ava, s.getOutputStream());
			
			System.out.println("Hecho");
			s.close();
			// Decoding the specified input stream
		}catch (Exception e){
				System.out.println("Petardazo!");
				e.printStackTrace();
		}

	}

}
