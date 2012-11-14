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

import es.libresoft.openhealth.utils.DIM_Tools;

public class Attribute {
	/* Qualifier of this attribute */
	public enum Qualifier{
		Mandatory,
		Conditional,
		Recomended,
		NotRecomended,
		Optional
	}
	
	private String attrName; 				/* Friendly name of this attribute */
	private int attrId; 					/* Attribute ID given from ISO/IEEE 11073/10101 */
	private Object attrType; 	/* The ASN.1 definition class given from Annex A */
	//private Qualifier qual;					/* qualifier of this attribute, it may be optional, mandatory or conditional */
	
	public Attribute (int id, Object type) throws InvalidAttributeException {
		if (!((0<=id)&&(id<=65535)))
			throw new InvalidAttributeException("ID: " + id +" is not in ISO/IEEE 11073-10101 partition");
		String name = DIM_Tools.getAttributeName(id);
		if (name == null) throw new InvalidAttributeException("Name not found for id " + id +".");
		this.attrName = name;
		this.attrId = id;
		this.attrType = type;
	}
	
	/**
	 * Get friendly name for this attribute
	 * @return Attribute name
	 */
	public String getAttributeName() {
		return attrName;
	}
	
	/**
	 * Get nomenclature reference ID for this attribute
	 * @return Attribute_ID
	 */
	public int getAttributeID()
	{
		return attrId;
	}
	
	/**
	 * Get the ASN.1 attribute type
	 * @return Attribute Type
	 */
	public Object getAttributeType (){
		return attrType;
	}
	
	/*
	public Qualifier getQualifier (){
		return this.qual;
	}
	*/
}
