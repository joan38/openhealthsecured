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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.types.BitString;


public class TestManagerStateTable29 {

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
	  
	  public static void main(String[] args) {
			byte[] byteArray = {(byte)255, (byte)128};
			

			try {
				
				/* TEST ApduType choice */
				/* Make ApduType */
				ApduType at = new ApduType();
				
				/* Make ApduType -> AarqApdu */
				AarqApdu aarq = new AarqApdu();
				
				/* Make ApduType -> AarqApdu -> AssociationVersion */ 
				AssociationVersion av = new AssociationVersion();
				
				/* Make ApduType -> AarqApdu -> AssociationVersion -> Bits-32 1(0)*/
				byte[] ascVr = {(byte)128, (byte)0, (byte)0, (byte)0};
				
				/* Set Value AssociationVersion */
				BitString bt = new BitString (ascVr);
				av.setValue(bt);
				
				/* Make ApduType -> AarqApdu -> DataProtoList */
				DataProtoList dpl = new DataProtoList();
				
				/* Initialize  DataProtoList */
				dpl.initValue();
				
				/* Make ApduType -> AarqApdu -> DataProtoList -> DataProto */
				DataProto dp = new DataProto();
				
				/* Make ApduType -> AarqApdu -> DataProtoList -> DataProto -> DataProtoId */
				DataProtoId dpi= new DataProtoId(20601);
				
				/*BEGIN to encode PhdAssociationInformation in ANY form through ByteArrayOutputStream to get bytes array*/
				
				/* Make PhdAssociationInformation */
				PhdAssociationInformation pai = new PhdAssociationInformation();
				
				/* Make PhdAssociationInformation -> ProtocolVersion */
				ProtocolVersion pv = new ProtocolVersion();
				
				/* Make PhdAssociationInformation -> ProtocolVersion --> Bits-32 1(0) */
				byte[] proVr = {(byte)128, (byte)0, (byte)0, (byte)0};
				
				/* Set Value PhdAssociationInformation -> ProtocolVersion */
				BitString pbt = new BitString (proVr);
				pv.setValue(pbt);
				
				/* Make 16 BITS  MDER 0(0) [128], XER 1(0) [192], PER 2(0) [224]*/
				byte[] encR = {(byte)224, (byte)0};
				
				/* Make & Set, PhdAssociationInformation -> EncodingRules -> Bits-16 1(0) */
				EncodingRules er = new EncodingRules(new BitString(encR));
				
				/* Make & Set PhdAssociationInformation -> NomenclatureVersion -> Bits-32 1(0) */
				byte[] noVr = {(byte)128, (byte)0, (byte)0, (byte)0};
				
				/* Make & Set PhdAssociationInformation -> NomenclatureVersion*/
				BitString nbt = new BitString (noVr);
				NomenclatureVersion nv = new NomenclatureVersion(new BitString(noVr));
				
				/* Make PhdAssociationInformation -> FunctionalUnits -> Bits-32 0(0) */
				byte[] byteArray2 = {(byte)0, (byte)0, (byte)0, (byte)0};
				FunctionalUnits fn = new FunctionalUnits(new BitString(byteArray2));
				
				/* Make PhdAssociationInformation -> SystemType -> Bits-32 8(0)*/
				byte[] syst = {(byte)0, (byte)128, (byte)0, (byte)0}; 
				SystemType st = new SystemType(new BitString(syst));
				
				/* ¡¡¡Revisar esto!!! */
				
				/*byte[] sysId = {(byte)76, (byte)78, (byte)73, (byte)95, (byte)83, (byte)89, (byte)83,
								(byte)84, (byte)69, (byte)77, (byte)95, (byte)73, (byte)68};*/
				

				byte[] sysId = {(byte)31, (byte)32, (byte)33, (byte)34, (byte)35, (byte)36, (byte)37, (byte)38};
				
				/* Make PhdAssociationInformation -> ConfigId  Thermometer Range Value Std[800] Ext[16386,32767]*/
				ConfigId cid = new ConfigId(800);
				
				
				/* Make PhdAssociationInformation -> DataReqModeCapab */
				DataReqModeCapab drmc = new DataReqModeCapab();
				
				/* Make PhdAssociationInformation -> DataReqModeCapab -> DataReqModeFlags */
				DataReqModeFlags drmf = new DataReqModeFlags();
				
				/* Set PhdAssociationInformation -> DataReqModeCapab -> DataReqModeFlags (0) "single response" */
				byte[] drmfb = {(byte)128, (byte)0};
				drmf.setValue(new BitString(drmfb));
				
				/* Set PhdAssociationInformation -> DataReqModeCapab */
				drmc.setData_req_mode_flags(drmf);
				drmc.setData_req_init_agent_count(1);
				drmc.setData_req_init_manager_count(0);
				
				/* Make PhAssociationInformation -> AttributeList */
				AttributeList al = new AttributeList();
				
				/* Set PhdAssociationInformation -> AttributeList */
				al.initValue();
				
				/* Set PhdAssociationInformation  */
				pai.setProtocol_version(pv);
				pai.setEncoding_rules(er);
				pai.setNomenclature_version(nv);
				pai.setFunctional_units(fn);
				pai.setSystem_type(st);
				pai.setSystem_id(sysId);
				pai.setDev_config_id(cid);
				pai.setData_req_mode_capab(drmc);
				pai.setOption_list(al);
				/* End Set PhdAssociationInformation */
				
				/* Encode PhdAssociationInformation */
				ByteArrayOutputStream out= new ByteArrayOutputStream();
				IEncoder<PhdAssociationInformation> encoderPHD = CoderFactory.getInstance().newEncoder("MDER");
				encoderPHD.encode(pai, out);
				/*End encode PhdAssociationInformation*/
				
				/* Transform Bytes to Array*/
				/* Set DataProto ApduType -> AarqApdu -> DataProtoList -> DataProto */
				dp.setData_proto_id(dpi);
				/* Set ApduType -> AarqApdu -> DataProtoList -> DataProto -> DataProtoId */
				/* Transform Encode PhdAssociationInformation to Bytes Array*/
				dp.setData_proto_info(out.toByteArray()); //Get bytes for PhdAssociationInformation
				/* Add DataProto to ApduType -> AarqApdu -> DataProtoList */
				dpl.add(dp);
				/* Add AssociationVersion to ApduType -> AarqApdu -> AssociationVersion*/
				aarq.setAssoc_version(av);
				/* Add DataProtoList to ApduType -> AarqApdu ->DataProtoList */
				aarq.setData_proto_list(dpl);
				/* Add ArqApdu to ApduType -> AarqApdu */
				at.selectAarq(aarq);
				
				//Coding the data on the net:
				IEncoder<ApduType> encoder = CoderFactory.getInstance().newEncoder("MDER");
				IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
				
				System.out.println("Conectando con el servidor...");
				Socket s = new Socket("127.0.0.1",9999);
				
				encoder.encode(at, s.getOutputStream());
				ApduType apdu = decoder.decode(s.getInputStream(), ApduType.class);
				if (apdu.isAareSelected()){
					System.out.println("Initializing ...");
					AareApdu aare = apdu.getAare();
					System.out.println("AssociateResult: "+aare.getResult().getValue());
					int associateresult = aare.getResult().getValue();
					DataProto dataproto = aare.getSelected_data_proto();
					System.out.println("DataProtoID: "+dataproto.getData_proto_id().getValue());
					int dataprotoid = dataproto.getData_proto_id().getValue();
					
					if (associateresult == 0){
						System.out.println("Accepted");						
						byte [] phdinfo = dataproto.getData_proto_info();
						System.out.println("DataProtoInfo: "+(getHexString(phdinfo)));
						
						if (getHexString(phdinfo).isEmpty()){
							System.out.println("Error:: DataProtoInfo is Null");								
						}else{													
							ByteArrayInputStream input = new ByteArrayInputStream(phdinfo);						
							PhdAssociationInformation phd = decoder.decode(input, PhdAssociationInformation.class);
							
							System.out.println("ProtocolVersion: "+ getHexString(phd.getProtocol_version().getValue().getValue()));
							System.out.println("EncondingRules: "+ getHexString(phd.getEncoding_rules().getValue().getValue()));
							System.out.println("NomenclatureVersion: "+ getHexString(phd.getNomenclature_version().getValue().getValue()));
							System.out.println("FunctionalUnits: "+ getHexString(phd.getFunctional_units().getValue().getValue()));
							System.out.println("SystemType: "+ getHexString(phd.getSystem_type().getValue().getValue()));
							System.out.println("SystemId: "+ getHexString(phd.getSystem_type().getValue().getValue()));
							System.out.println("DevConfigId: "+ phd.getDev_config_id().getValue());
							System.out.println("DataReqModeFlags: "+ getHexString(phd.getData_req_mode_capab().getData_req_mode_flags().getValue().getValue()));
							System.out.println("DataReqInitAgentCount: "+ phd.getData_req_mode_capab().getData_req_init_agent_count());
							System.out.println("DataReqInitManagerCount:"+ phd.getData_req_mode_capab().getData_req_init_manager_count());
							Collection<AVA_Type> avac = phd.getOption_list().getValue();
							Iterator<AVA_Type> i = avac.iterator();
							while (i.hasNext()){
								AVA_Type ava = i.next();
								System.out.println("Attribute-id:" + ava.getAttribute_id().getValue().getValue());
								System.out.println("Attribute-value:" + getHexString(ava.getAttribute_value()));
							}
						}
					}else if (associateresult == 1){
						System.out.println("Rejected Permanent");						
					}else if (associateresult == 2){
						System.out.println("Rejected Transient");
					}else if (associateresult == 3){
						System.out.println("Accepted Unknown Config");
					}else if (associateresult == 4){
						System.out.println("Rejected No Common Protocol");
						
						if (dataprotoid == 0){
							byte [] phdinfo = dataproto.getData_proto_info();
							System.out.println("DataProtoInfo: "+(getHexString(phdinfo)));
							if (getHexString(phdinfo).isEmpty()){
								System.out.println("Passed Test!");								
							}else{
								System.out.println("DataProtoInfo Must Be Null");
							}							
						}else {
							System.out.println("DataProtoId Must Have Value : 0");
						}
					}else if (associateresult == 5){
						System.out.println("Rejected No Common Parameter");
						
						if (dataprotoid == 0){
							byte [] phdinfo = dataproto.getData_proto_info();
							System.out.println("DataProtoInfo: "+(getHexString(phdinfo)));
							if (getHexString(phdinfo).isEmpty()){
								System.out.println("Passed Test!");								
							}else{
								System.out.println("DataProtoInfo Must Be Null");
							}							
						}else {
							System.out.println("DataProtoId Must Have Value : 0");
						}
					}else if (associateresult == 6){
						System.out.println("Rejected Unknown");
					}else if (associateresult == 7){
						System.out.println("Rejected Unauthorized");
					}else if (associateresult == 8){
						System.out.println("Unsupported Association Version");
						
						if (dataprotoid == 0){
							byte [] phdinfo = dataproto.getData_proto_info();
							System.out.println("DataProtoInfo: "+(getHexString(phdinfo)));
							if (getHexString(phdinfo).isEmpty()){
								System.out.println("Passed Test!");								
							}else{
								System.out.println("DataProtoInfo Must Be Null");
							}							
						}else {
							System.out.println("DataProtoId Must Have Value : 0");
						}
					}else {
						System.out.println("Unknown Message");
						
					}					
					
				}else System.out.println("Problem!");
			}catch (Exception e){
				System.out.println("Error!");
				e.printStackTrace();
		}
			
	  }
}