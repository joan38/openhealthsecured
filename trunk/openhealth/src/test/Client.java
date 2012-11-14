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
import org.bn.types.BitString;


public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AarqApdu aarq = new AarqApdu();
		AssociationVersion version = new AssociationVersion();
		String v = "1";
		BitString b = new BitString(v.getBytes());
		System.out.println("longitud: " + b.getLength() + ", " + b.getValue());
		version.setValue(b);
		
		DataProtoList c = new DataProtoList ();
		DataProto p = new DataProto();
		DataProtoId d = new DataProtoId();
		//d.setValue(DataProtoId.EnumType.data_proto_id_20601);
		p.setData_proto_id(d);
		c.initValue();
		c.add(p);
		aarq.setAssoc_version(version);
		aarq.setData_proto_list(c);
		// Encoding for Java
		try {
		IEncoder<AarqApdu> encoder = CoderFactory.getInstance().newEncoder("PER");
		IDecoder decoder = CoderFactory.getInstance().newDecoder("PER");
		
		System.out.println("Conectando con el servidor...");
		Socket s = new Socket("127.0.0.1",9999);
		System.out.println("Enviando " + aarq.getAssoc_version().getValue());
		
		encoder.encode(aarq, s.getOutputStream());
		
		//System.out.println("Reciviendo respuesta...");
		//NomPartition np2 = decoder.decode(s.getInputStream(), NomPartition.class);
		
		//System.out.println("Recivida petición " + np2.getValue().name());
		System.out.println("BEEP");
		s.close();
		// Decoding the specified input stream
		}catch (Exception e){
			System.out.println("Petardazo!");
			e.printStackTrace();
		}

	}

}
