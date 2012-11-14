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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.annotations.ASN1PreparedElement;
import org.bn.coders.DecodedObject;
import org.bn.types.BitString;

import es.libresoft.mdnf.FloatType;


public class TestManagerConnection {
	
	private static Integer InvokeId;
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

	private static void ProcessPhdAssociationInformation (byte[] phfInfo) throws Exception {
		System.out.println("      data-proto-info: (ANY DEFINED BY data-proto-id=data-proto-id-20601 PhdAssociationInformation ::={");
		IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
		ByteArrayInputStream input = new ByteArrayInputStream(phfInfo);
		PhdAssociationInformation phd = decoder.decode(input, PhdAssociationInformation.class);
		System.out.println("        protocol-version:" + getHexString(phd.getProtocol_version().getValue().getValue()));
		System.out.println("        encoding-rules: " + getHexString(phd.getEncoding_rules().getValue().getValue()));
		System.out.println("        nomenclature-version: " + getHexString(phd.getNomenclature_version().getValue().getValue()));
		System.out.println("        functional-units: " + getHexString(phd.getFunctional_units().getValue().getValue()));
		System.out.println("        system-type: " + getHexString(phd.getSystem_type().getValue().getValue()));
		System.out.println("        system-id: " + getHexString(phd.getSystem_id()));
		System.out.println("        dev-config-id: " + phd.getDev_config_id().getValue());
		System.out.println("        data-req-mode-capab: DataReqModeCapab ::={");
		System.out.println("          data-req-mode-flags: " + 
				getHexString(phd.getData_req_mode_capab().getData_req_mode_flags().getValue().getValue()));
		System.out.println("          data-req-init-agent-count:" + phd.getData_req_mode_capab().getData_req_init_agent_count());
		System.out.println("          data-req-init-agent-count:" + phd.getData_req_mode_capab().getData_req_init_manager_count());
		System.out.println("          }");
		System.out.println("        option-list: AttributeList ::={");
		Collection<AVA_Type> avac = phd.getOption_list().getValue();
		Iterator<AVA_Type> i = avac.iterator();
		while (i.hasNext()){
			AVA_Type ava = i.next();
			System.out.println("          attribute-id:" + ava.getAttribute_id().getValue().getValue());
			System.out.println("          attribute-value:" + getHexString(ava.getAttribute_value()));
		}
		System.out.println("        }");
		System.out.println("      }");
	}
	public static void ProcessAarqApdu (AarqApdu aarq) {
		try {
		System.out.println("AarqApdu ::= {");
			System.out.println("  assoc-version: " + getHexString(aarq.getAssoc_version().getValue().getValue()));
			System.out.println("  data-proto-list: DataProtolist ::= {");
			Collection<DataProto> list = aarq.getData_proto_list().getValue();
			Iterator<DataProto> i = list.iterator();
			while (i.hasNext()){
				DataProto dp = i.next();
				System.out.println("    DataProto ::= {");
				System.out.println("      data-proto-id: " + dp.getData_proto_id().getValue());
				if (dp.getData_proto_id().getValue() == 20601) {
					//If the data-proto-id is set to data-proto-id-20601, the data-proto-info shall
					// be filled with a PhdAssociationInformation structure
					ProcessPhdAssociationInformation(dp.getData_proto_info());					
				}
				System.out.println("    }");
			}
			System.out.println("  }");
		System.out.println("}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void ProcessObsValData(byte[] data) throws Exception{
		/*
		IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
		ByteArrayInputStream input = new ByteArrayInputStream(data);
		SimpleNuObsValue snov = decoder.decode(input, SimpleNuObsValue.class);
		*/
		FloatType ft = new FloatType(0xFF000270);
		//System.out.println("          byteValue: " + getHexString(data));
		System.out.println("          Value: " + ft);
	}
	private static void ProcessScanReportInfoFixed(byte[] srifB) throws Exception{
		System.out.println("    event-info:  (ANY DEFINED BY event-type=MDC_NOTI_SCAN_REPORT_FIXED) ScanReportInfoFixed ::= {");
		IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
		ByteArrayInputStream input = new ByteArrayInputStream(srifB);
		ScanReportInfoFixed srif = decoder.decode(input, ScanReportInfoFixed.class);
		
		System.out.println("      data-req-id: " + srif.getData_req_id().getValue());
		System.out.println("      scan-report-no: " + srif.getScan_report_no());
		System.out.println("      obs-scan-fixed: obs-scan-fixed ::= {");
		Collection<ObservationScanFixed> list = srif.getObs_scan_fixed();
		Iterator<ObservationScanFixed> i = list.iterator();
		while (i.hasNext()){
			ObservationScanFixed osf = i.next();
			System.out.println("        obj-handle: " + osf.getObj_handle().getValue().getValue());
			System.out.println("        obs-val-data: " + getHexString(osf.getObs_val_data()));
			//ProcessObsValData(osf.getObs_val_data());
			System.out.println("        } ");
		}
		System.out.println("      }");
		System.out.println("    }");
	}
	
	public static void ProcessPrstApdu (PrstApdu pa) throws Exception{
		IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
		ByteArrayInputStream is = new ByteArrayInputStream(pa.getValue());
		DataApdu da = decoder.decode(is, DataApdu.class);
		
		System.out.println("DataApdu ::={");
		System.out.println("  invoke-id: " + da.getInvoke_id().getValue());
		InvokeId = da.getInvoke_id().getValue();
		
		if (da.getMessage().isRoiv_cmip_confirmed_event_reportSelected()){
			DataApdu.MessageChoiceType msg = da.getMessage();
			System.out.println("  message: EventReportArgumentSimple ::= {");
			System.out.println("    obj-handle:" + 
					msg.getRoiv_cmip_confirmed_event_report().getObj_handle().getValue().getValue());
			System.out.println("    event-time:" + 
					msg.getRoiv_cmip_confirmed_event_report().getEvent_time().getValue().getValue());
			System.out.println("    event-type:" +
					msg.getRoiv_cmip_confirmed_event_report().getEvent_type().getValue().getValue());
			ProcessScanReportInfoFixed(msg.getRoiv_cmip_confirmed_event_report().getEvent_info());
		}
		
		System.out.println("}");
	}
	
	public static void ProcessAbrtApdu (AbrtApdu aa){
		System.out.println("AbrtApdu ::= {");
		System.out.println("  reason: " + aa.getReason().getValue());
		System.out.println("}");
	}
	
	public static void sendPrstApdu (OutputStream ou) throws Exception{
		//Create PRST response
		ApduType at = new ApduType();
		PrstApdu pa = new PrstApdu();
		DataApdu da = new DataApdu();
		InvokeIDType iit = new InvokeIDType(InvokeId);
		DataApdu.MessageChoiceType msg = new DataApdu.MessageChoiceType();
		EventReportResultSimple errs = new EventReportResultSimple();
		HANDLE h = new HANDLE();
		h.setValue(new INT_U16(0));
		RelativeTime rt = new RelativeTime();
		rt.setValue(new INT_U32(0x00FFFFFFFFL));
		OID_Type ot = new OID_Type();
		ot.setValue(new INT_U16(new Integer(3357)));
		byte[] byteArray = {(byte)0, (byte)0};
		errs.setObj_handle(h);
		errs.setCurrentTime(rt);
		errs.setEvent_type(ot);
		errs.setEvent_reply_info(byteArray);
		msg.selectRors_cmip_confirmed_event_report(errs);
		da.setInvoke_id(iit);
		da.setMessage(msg);
		
		ByteArrayOutputStream output= new ByteArrayOutputStream();
		IEncoder<DataApdu> encoderDataApdu = CoderFactory.getInstance().newEncoder("MDER");
		encoderDataApdu.encode(da, output);
		
		pa.setValue(output.toByteArray());
		at.selectPrst(pa);
		
		//Patch for code the data on the net with the length header:
		encodeData(at,ou);
	}
	public static void sendAareApdu (OutputStream ou) throws Exception{		
		//Create AARE response
		ApduType at = new ApduType();
		AareApdu aare = new AareApdu();
		AssociateResult ar = new AssociateResult(0);
		DataProto dp = new DataProto();
		DataProtoId dpi= new DataProtoId(20601);
		
		
		/*BEGIN to encode PhdAssociationInformation in ANY form through ByteArrayOutputStream to get bytes array*/
		PhdAssociationInformation pai = new PhdAssociationInformation();
		ProtocolVersion pv = new ProtocolVersion();
		byte[] pvA = {(byte)128, (byte)0, (byte)0, (byte)0};
		BitString bt = new BitString (pvA);
		pv.setValue(bt);		
		byte[] encR = {(byte)128, (byte)0};
		EncodingRules er = new EncodingRules(new BitString(encR));
		NomenclatureVersion nv = new NomenclatureVersion(new BitString(pvA));
		byte[] byteArray = {(byte)0, (byte)0, (byte)0, (byte)0};
		FunctionalUnits fn = new FunctionalUnits(new BitString(byteArray));
		SystemType st = new SystemType(new BitString(pvA));
		byte[] sysId = {(byte)76, (byte)78, (byte)73, (byte)95, (byte)77, (byte)71, (byte)82, (byte)48};
		ConfigId cid = new ConfigId(0);
		DataReqModeCapab drmc = new DataReqModeCapab();
		DataReqModeFlags drmf = new DataReqModeFlags();
		byte[] drmfb = {(byte)0, (byte)0};
		drmf.setValue(new BitString(drmfb));
		drmc.setData_req_mode_flags(drmf);
		drmc.setData_req_init_agent_count(0);
		drmc.setData_req_init_manager_count(0);
		AttributeList al = new AttributeList();
		al.initValue();
		pai.setProtocol_version(pv);
		pai.setEncoding_rules(er);
		pai.setNomenclature_version(nv);
		pai.setFunctional_units(fn);
		pai.setSystem_type(st);
		pai.setSystem_id(sysId);
		pai.setDev_config_id(cid);
		pai.setData_req_mode_capab(drmc);
		pai.setOption_list(al);
		ByteArrayOutputStream output= new ByteArrayOutputStream();
		IEncoder<PhdAssociationInformation> encoderPHD = CoderFactory.getInstance().newEncoder("MDER");
		encoderPHD.encode(pai, output);
		/*END to encode PhdAssociationInformation*/
		
		dp.setData_proto_id(dpi);
		dp.setData_proto_info(output.toByteArray());
		aare.setResult(ar);
		aare.setSelected_data_proto(dp);
		at.selectAare(aare);
		
		//Patch for code the data on the net with the length header:
		encodeData(at,ou);
	}
	
	/*
	 * VASC send the length at begin of the packet, i dont't see it in IEEE 20101 specification
	 * To solve this patch read two bytes and continue...
	 */
	public static<T> T decodeData(InputStream stream, Class<T> objectClass) throws Exception{
		//Read two bytes (Length of the packet data): This is not part of MDER specification. BinaryNotes checks all
		//length constraints of all data types. This operation is not neccesary.
		/*
		int i=1;
		byte[] hex = new byte[1];				
		while ((i<3)&&((i=stream.read())!=-1)){
			hex[0]=(byte)i;
		}
		*/
		//Get the data in mder codification
		IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
		return decoder.decode(stream, objectClass);
	}
	
	/*
	 * VASC send the length at begin of the packet, i dont't see it in IEEE 20101 specification
	 * To solve this patch write two bytes length representation and continue...
	 */
	public static <T> void encodeData(T object, OutputStream stream) throws Exception {
		//Patch for to code the data on the net with the length header:
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IEncoder<T> encoder = CoderFactory.getInstance().newEncoder("MDER");
		encoder.encode(object, os);
		
		//Write the data
		/*
		IEncoder<INT_U16> coder = CoderFactory.getInstance().newEncoder("MDER");
		INT_U16 iu = new INT_U16(new Integer(os.size()));
		coder.encode(iu, stream);
		*/
		stream.write(os.toByteArray());
	}
	
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server running at " + ss.getInetAddress() + ":" + ss.getLocalPort());
			while (true){
				System.out.println("Waiting for clients...");
				ThreadServer t = new ThreadServer(ss.accept());
				t.run();
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public static class ThreadServer implements Runnable {
		private Socket s;
		private boolean vstop;
		public ThreadServer(Socket client) {
	        this.s = client;
	        vstop = false;
	    }
		
		public void run() {
			System.out.println("Starting new thread...");
			InputStream is = null;
			OutputStream ou = null;
			try {
				is = s.getInputStream();
				ou = s.getOutputStream();
				System.out.println("Recivida petición...");
				while(!vstop){
					ApduType apdu = decodeData(is, ApduType.class);
					if (apdu.isAarqSelected()) {
						System.out.println(">> [AarqApdu]");
						ProcessAarqApdu(apdu.getAarq());
						sendAareApdu(ou);
					}else if (apdu.isPrstSelected()){
						System.out.println(">> [PrstApdu]");
						ProcessPrstApdu(apdu.getPrst());
						sendPrstApdu(ou);
					}else if (apdu.isAbrtSelected()){
						System.out.println(">> [AbrtApdu]");
						ProcessAbrtApdu(apdu.getAbrt());
						vstop = true;
					}else System.out.println("I don't kwnow what i received!");
				}
				System.out.println("Thread exiting...");
				is.close();
				ou.close();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		public void stop(){
			System.out.println("Closing connection with client...");
			vstop=true;
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
