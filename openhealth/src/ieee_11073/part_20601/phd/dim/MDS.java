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
package ieee_11073.part_20601.phd.dim;

	/**
	 * This is the MDS class. 
	 * The top-level object of each agent is instantiated from the MDS class. Each agent
	 * has one MDS object. The MDS represents the identification and status of the agent 
	 * through its attributes.
	 */

import java.nio.charset.Charset;
import java.util.Hashtable;
import es.libresoft.openhealth.DeviceConfig;
import es.libresoft.openhealth.ManagerConfig;
import ieee_11073.part_10101.Nomenclature;
import ieee_11073.part_20601.asn1.ConfigId;
import ieee_11073.part_20601.asn1.HANDLE;
import ieee_11073.part_20601.asn1.INT_U16;
import ieee_11073.part_20601.asn1.OID_Type;
import ieee_11073.part_20601.asn1.SystemModel;
import ieee_11073.part_20601.asn1.TypeVer;
import ieee_11073.part_20601.asn1.TypeVerList;

	/**
	 * Each personal health device agent is defined by an object-oriented model. The top-level object
	 * of each agent is instantiated from the MDS class. Each agent has one MDS object. The MDS represents
	 * the identification and status of the agent through its attributes.
	 */

public abstract class MDS extends DIM implements MDS_Events, GET_Service {
	
	private static int[] mandatoryIds = {Nomenclature.MDC_ATTR_ID_HANDLE,
						  Nomenclature.MDC_ATTR_ID_MODEL,
						  Nomenclature.MDC_ATTR_SYS_ID,
						  Nomenclature.MDC_ATTR_DEV_CONFIG_ID};
	
	private DeviceConfig dev_conf;
	
	private Hashtable<Integer,Scanner> scanners;
	private Hashtable<Integer,Numeric> numerics;
	private Hashtable<Integer,RT_SA> rt_sas;
	private Hashtable<Integer,Enumeration> enumerations;
	private Hashtable<Integer,PM_Store> pm_stores;
	
	/**
	 * Used only in extended configuration when the agent configuration is unknown
	 */
	public MDS(byte[] system_id, ConfigId devConfig_id){
		this.attributeList = generateMandatoryMDSAttributes(system_id, devConfig_id);
		clearObjectsFromMds();
	}
	
	public MDS(Hashtable<Integer,Attribute> attributes) throws InvalidAttributeException {
		super(attributes);
		clearObjectsFromMds();
	}
	
	public boolean setDeviceConfig(DeviceConfig dev_conf){
		if (this.dev_conf == null){
			this.dev_conf = dev_conf;
			return true;
		}else 
			return false;
	}
	
	public DeviceConfig getDeviceConf(){return this.dev_conf;}
	
	protected void clearObjectsFromMds () {
		scanners = new Hashtable<Integer,Scanner>();
		numerics = new Hashtable<Integer,Numeric>();
		rt_sas = new Hashtable<Integer,RT_SA>();
		enumerations = new Hashtable<Integer,Enumeration>();
		pm_stores = new Hashtable<Integer,PM_Store>();
	}
	
	@Override
	protected void checkAttributes(
			Hashtable<Integer, Attribute> attributes)
			throws InvalidAttributeException {
		/* Check mandatory attributes */
		for (int i=0; i<mandatoryIds.length; i++){
			if (!attributes.containsKey(mandatoryIds[i]))
				throw new InvalidAttributeException("Attribute id " + mandatoryIds[i] + " is not assigned.");
		}
	}
	
	public int getNomenclatureCode (){
		return Nomenclature.MDC_MOC_VMS_MDS_SIMP;
	}
	
	public Scanner getScanner (HANDLE handle){
		return scanners.get(handle.getValue().getValue());
	}
	
	public void addScanner (Scanner scanner){
		HANDLE handle = (HANDLE)scanner.getAttribute(Nomenclature.MDC_ATTR_ID_HANDLE).getAttributeType();
		scanners.put(handle.getValue().getValue(), scanner);
	}
	
	public Numeric getNumeric (HANDLE handle){
		return numerics.get(handle.getValue().getValue());
	}
	
	public void addNumeric (Numeric numeric){
		HANDLE handle = (HANDLE)numeric.getAttribute(Nomenclature.MDC_ATTR_ID_HANDLE).getAttributeType();
		numerics.put(handle.getValue().getValue(), numeric);
	}
	
	public RT_SA getRT_SA (HANDLE handle){
		return rt_sas.get(handle.getValue().getValue());
	}
	
	public Enumeration getEnumeration (HANDLE handle){
		return enumerations.get(handle.getValue().getValue());
	}
	
	public PM_Store getPM_Store (HANDLE handle){
		return pm_stores.get(handle.getValue().getValue());
	}
	
	/* MDS Object methods */
	
	/**
	 * This method allows the manager system to enable or disable measurement data transmission 
	 * from the agent (see 8.9.3.3.3 for a description).
	 */
	public abstract void MDS_DATA_REQUEST ();
	
	/**
	 * This method allows the manager system to set a real-time clock (RTC) with the 
	 * absolute time. The agent indicates whether the Set-Time command is valid by 
	 * using the mds-time-capab-set-clock bit in the Mds-Time-Info attribute.
	 */
	public abstract void Set_Time ();
	
	
	//----------------------------------PRIVATE----------------------------------------
	private Hashtable<Integer,Attribute> generateMandatoryMDSAttributes (byte[] system_id, ConfigId devConfig_id){
		Hashtable<Integer,Attribute> mandatoryAttributes = new Hashtable<Integer,Attribute>();
		try {
			//MDS Handle=0
			HANDLE handle = new HANDLE();
			handle.setValue(new INT_U16(new Integer(0)));
			mandatoryAttributes.put(Nomenclature.MDC_ATTR_ID_HANDLE, 
					new Attribute(Nomenclature.MDC_ATTR_ID_HANDLE,
									handle));
			
			//{"Manufacturer","Model"}
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mandatoryAttributes;
	}
}
