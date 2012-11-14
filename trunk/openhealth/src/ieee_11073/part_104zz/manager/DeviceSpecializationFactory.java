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
package ieee_11073.part_104zz.manager;

import java.nio.charset.Charset;
import java.util.Hashtable;

import ieee_11073.part_10101.Nomenclature;
import ieee_11073.part_20601.asn1.ConfigId;
import ieee_11073.part_20601.asn1.HANDLE;
import ieee_11073.part_20601.asn1.INT_U16;
import ieee_11073.part_20601.asn1.OID_Type;
import ieee_11073.part_20601.asn1.SystemModel;
import ieee_11073.part_20601.asn1.TypeVer;
import ieee_11073.part_20601.asn1.TypeVerList;
import ieee_11073.part_20601.phd.dim.Attribute;
import ieee_11073.part_20601.phd.dim.InvalidAttributeException;

/**
 * DeviceSpecializationFactory is used to instantiate an standard device specialization defined in 104zz
 * @author sancane
 */
public class DeviceSpecializationFactory {

	public static DS_Extended getExtendedMDS(byte[] system_id, ConfigId devConfig_id) {
		return new DS_Extended(system_id, devConfig_id);
	}
	
	public static DS_10408 getThermometer10408 (byte[] system_id, ConfigId devConfig_id){
		DS_10408 thermometer = null;
		
		Hashtable<Integer,Attribute> mandatoryAttributes = new Hashtable<Integer,Attribute>();
		try {
			//from Part 10408: Handle=0
			HANDLE handle = new HANDLE();
			handle.setValue(new INT_U16(new Integer(0)));
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_ID_HANDLE, 
					new Attribute(Nomenclature.MDC_ATTR_ID_HANDLE,
									handle));
			
			//from Part 10408: {"Manufacturer","Model"}
			Charset ch = Charset.forName("ASCII");
			SystemModel systemModel = new SystemModel();
			systemModel.setManufacturer("Manufacturer".getBytes(ch));
			systemModel.setModel_number("Model".getBytes(ch));
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_ID_MODEL, 
					new Attribute(Nomenclature.MDC_ATTR_ID_MODEL,
									systemModel));
			
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_SYS_ID, 
					new Attribute(Nomenclature.MDC_ATTR_SYS_ID,
									system_id));
			
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_DEV_CONFIG_ID, 
					new Attribute(Nomenclature.MDC_ATTR_DEV_CONFIG_ID,
									devConfig_id));
			
			//from Part 10408: {MDC_DEV_SPEC_PROFILE_TEMP,1}
			TypeVerList syst_type_data_list = new TypeVerList();
			syst_type_data_list.initValue();
			TypeVer item = new TypeVer();
			OID_Type oid = new OID_Type();
			oid.setValue(new INT_U16(new Integer(Nomenclature.MDC_DEV_SPEC_PROFILE_TEMP)));
			item.setType(oid);
			item.setVersion(new Integer(1));
			syst_type_data_list.add(item);
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_SYS_TYPE_SPEC_LIST, 
					new Attribute(Nomenclature.MDC_ATTR_SYS_TYPE_SPEC_LIST,
									syst_type_data_list));
			thermometer = new DS_10408 (mandatoryAttributes);
			
		} catch (InvalidAttributeException e) {/*Never thrown in standadard configuration*/
			e.printStackTrace();}
		return thermometer;
	}
}
