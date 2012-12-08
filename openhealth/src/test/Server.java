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

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.types.BitString;

import ieee_11073.part_20601.asn1.*;

public class Server {

    /**
     * @param args
     * @throws Exception
     */
    public static byte[] getBytes(PhdAssociationInformation obj) throws Exception {
        IEncoder<PhdAssociationInformation> encoder = CoderFactory.getInstance().newEncoder("PER");
        //Serializable mySerializableObj = new PhdAssociationInformation(obj);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        encoder.encode(obj, bos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        bos.close();
        byte[] data = bos.toByteArray();
        return data;
    }

    public static void main(String[] args) {
        // Encoding for Java
        try {
            IEncoder<AarqApdu> encoder = CoderFactory.getInstance().newEncoder("PER");
            IEncoder<AareApdu> encoder2 = CoderFactory.getInstance().newEncoder("PER");
            IDecoder decoder = CoderFactory.getInstance().newDecoder("PER");
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("Server arrancado en " + ss.getInetAddress() + ":" + ss.getLocalPort());
            while (true) {
                System.out.println("Esperando conexiones de clientes...");
                Socket s = ss.accept();
                System.out.println("Recivida petición...");
                try {
                    AarqApdu np = decoder.decode(s.getInputStream(), AarqApdu.class);
                    System.out.println("Recivido " + np.getAssoc_version().getValue());
                } catch (Exception e) {
                    AssociateResult asr = new AssociateResult();
                    //asr.setValue(AssociateResult.EnumType.accepted);
                    System.out.println("IEEESSS");

                    AareApdu aare = new AareApdu();
                    aare.setResult(asr);
                    DataProto dp = new DataProto();
                    DataProtoId dpi = new DataProtoId();

                    //dpi.setValue(DataProtoId.EnumType.data_proto_id_20601);
                    dp.setData_proto_id(dpi);

                    PhdAssociationInformation phd = new PhdAssociationInformation();
                    ProtocolVersion ptv = new ProtocolVersion();
                    ptv.setValue(new BitString(new byte[]{(byte) 0xAA}));
                    phd.setProtocol_version(ptv);

                    dp.setData_proto_info(getBytes(phd));
                    aare.setSelected_data_proto(dp);

                    encoder2.encode(aare, s.getOutputStream());
                }
                s.close();
            }
            // Decoding the specified input stream
        } catch (Exception e) {
            System.out.println("Petardazo!");
            e.printStackTrace();
        }


    }
}
