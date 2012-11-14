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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.types.BitString;

import sancane_test.MySequenceOf;

public class TestChoiceMDER {

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
	 */
	public static void main(String[] args) {
		byte[] byteArray = {(byte)255, (byte)128};
		byte[] byteArray2 = {(byte)0, (byte)0, (byte)0, (byte)0};
		
		//-----------------------------------------------------------------------
		/* TEST EnumVal choice */
		//EnumVal iu = new EnumVal();
		//iu.selectEnum_text_string(byteArray);
		//OID_Type oit = new OID_Type();
		//oit.setValue(new INT_U16(new Integer(9)));
		//iu.selectEnum_obj_id(oit);
		//iu.selectEnum_bit_str(new BitString(byteArray));
		//-----------------------------------------------------------------------
		
		/* TEST SegmSelection choice */
		//SegmSelection ss = new SegmSelection();
		//INT_U16 iu = new INT_U16(12);
		//ss.selectAll_segments(iu);
		//SegmIdList sil = new SegmIdList();
		//sil.initValue();
		//InstNumber in1 = new InstNumber(15);
		//InstNumber in2 = new InstNumber(14);
		//InstNumber in3 = new InstNumber(13);
		//sil.add(in1);
		//sil.add(in2);
		//sil.add(in3);
		//ss.selectSegm_id_list(sil);
		//AbsTimeRange atr = new AbsTimeRange();
		//AbsoluteTime at1 = new AbsoluteTime();
		//INT_U8 century,year,month,day,hour,minute,second,sec_fract;
		//century = new INT_U8(8);
		//year = new INT_U8(7);
		//month = new INT_U8(6);
		//day = new INT_U8(5);;
		//hour = new INT_U8(4);
		//minute = new INT_U8(3);
		//second = new INT_U8(2);
		//sec_fract = new INT_U8(1);
		//at1.setCentury(century);
		//at1.setYear(year);
		//at1.setMonth(month);
		//at1.setDay(day);
		//at1.setHour(hour);
		//at1.setMinute(minute);
		//at1.setSecond(second);
		//at1.setSec_fractions(sec_fract);
		//atr.setFrom_time(at1);
		//atr.setTo_time(at1);
		//ss.selectAbs_time_range(atr);
		//-----------------------------------------------------------------------
		
		/* TEST DataApdu choice */
		//DataApdu da = new DataApdu();
		//InvokeIDType iit = new InvokeIDType(16);
		//DataApdu.MessageChoiceType mct = new MessageChoiceType();
		//EventReportArgumentSimple eras = new EventReportArgumentSimple();
		//config HANDLE type
		//HANDLE h = new HANDLE();
		//h.setValue(new INT_U16(15));
		//config RealtiveTime
		//RelativeTime rt = new RelativeTime();
		//rt.setValue(new INT_U32(14L));
		//config OID_Type type
		//OID_Type oit = new OID_Type();
		//oit.setValue(new INT_U16(13));
		//config ANY type use byteArray
		//eras.setObj_handle(h);
		//eras.setEvent_time(rt);
		//eras.setEvent_type(oit);
		//eras.setEvent_info(byteArray);
		//mct.selectRoiv_cmip_event_report(eras);
		//mct.selectRoiv_cmip_confirmed_event_report(eras);
		//GetArgumentSimple gas = new GetArgumentSimple();
		//AttributeIdList ail = new AttributeIdList();
		//ail.initValue();
		//ail.add(oit);
		//gas.setObj_handle(h);
		//gas.setAttribute_id_list(ail);
		//mct.selectRoiv_cmip_get(gas);
		//da.setInvoke_id(iit);
		//da.setMessage(mct);
		
		try {
			
			/* TEST ApduType choice */
			ApduType at = new ApduType();
			AarqApdu aarq = new AarqApdu();
			AssociationVersion av = new AssociationVersion();
			byte[] ascVr = {(byte)128, (byte)0, (byte)0, (byte)0};
			BitString bt = new BitString (ascVr);
			av.setValue(bt);
			DataProtoList dpl = new DataProtoList();
			dpl.initValue();
			DataProto dp = new DataProto();
			DataProtoId dpi= new DataProtoId(20601);
			
			/*BEGIN to encode PhdAssociationInformation in ANY form through ByteArrayOutputStream to get bytes array*/
			PhdAssociationInformation pai = new PhdAssociationInformation();
			ProtocolVersion pv = new ProtocolVersion();
			pv.setValue(bt);
			byte[] encR = {(byte)128, (byte)0};
			EncodingRules er = new EncodingRules(new BitString(encR));
			NomenclatureVersion nv = new NomenclatureVersion(new BitString(ascVr));
			FunctionalUnits fn = new FunctionalUnits(new BitString(byteArray2));
			byte[] syst = {(byte)0, (byte)128, (byte)0, (byte)0};
			SystemType st = new SystemType(new BitString(syst));
			byte[] sysId = {(byte)76, (byte)78, (byte)73, (byte)95, (byte)83, (byte)89, (byte)83,
							(byte)84, (byte)69, (byte)77, (byte)95, (byte)73, (byte)68};
			ConfigId cid = new ConfigId(800);
			DataReqModeCapab drmc = new DataReqModeCapab();
			DataReqModeFlags drmf = new DataReqModeFlags();
			byte[] drmfb = {(byte)0, (byte)1};
			drmf.setValue(new BitString(drmfb));
			drmc.setData_req_mode_flags(drmf);
			drmc.setData_req_init_agent_count(1);
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
			ByteArrayOutputStream out= new ByteArrayOutputStream();
			IEncoder<PhdAssociationInformation> encoderPHD = CoderFactory.getInstance().newEncoder("MDER");
			encoderPHD.encode(pai, out);
			/*END to encode PhdAssociationInformation*/
			
			dp.setData_proto_id(dpi);
			dp.setData_proto_info(out.toByteArray()); //Get bytes for PhdAssociationInformation
			dpl.add(dp);
			aarq.setAssoc_version(av);
			aarq.setData_proto_list(dpl);
			at.selectAarq(aarq);
			
			//Coding the data on the net:
			IEncoder<ApduType> encoder = CoderFactory.getInstance().newEncoder("MDER");
			IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
			
			System.out.println("Conectando con el servidor...");
			Socket s = new Socket("127.0.0.1",9999);
			
			int i=0;
			for (;;){
				System.out.println("Send " + i);
				encoder.encode(at, s.getOutputStream());
				Thread.sleep(1000);
				i++;
			}
			/*
			System.out.println("Hecho");
			
			InputStream is = s.getInputStream();
			int i,index=0;
			byte[] hex = new byte[1];				
			while ((i=is.read())!=-1){
				hex[0]=(byte)i;
				System.out.println("*" + (index++) + ": " + getHexString(hex));
			}
			is.close();
			
			s.close();
			*/
			// Decoding the specified input stream
		}catch (Exception e){
				System.out.println("Petardazo!");
				e.printStackTrace();
		}

	}

}
