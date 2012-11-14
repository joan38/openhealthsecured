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
package es.libresoft.openhealth;

public class DeviceConfigCreator {

	private int data_proto_id;
	private String enc_rules;
	private int protocol_version;
	private int nomenclature_version;
	private int data_req_init_agent_count;
	private int data_req_init_manager_count;
	private byte[] data_req_mode_flags;
	
	public DeviceConfigCreator() {}
	
	public void setDataProtoId(int proto_id){this.data_proto_id = proto_id;}
	public void setEncondigRules(String rules){this.enc_rules = rules;}
	public void setProtocolVersion(int version){this.protocol_version = version;}
	public void setNomenclatureVersion(int version){this.nomenclature_version = version;}
	public void setDataReqInitAgentCount(int a_count){this.data_req_init_agent_count = a_count;}
	public void setDataReqInitManagerCount(int m_count){this.data_req_init_agent_count = m_count;}
	public void setDataReqModeFlags(byte[] flags){this.data_req_mode_flags = flags;}
	
	public DeviceConfig getDeviceConfig (){
		return new DeviceConfig(data_proto_id,enc_rules, protocol_version, nomenclature_version,
				data_req_init_agent_count, data_req_init_manager_count, data_req_mode_flags);
	}
}
