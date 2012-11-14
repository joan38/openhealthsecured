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
package es.libresoft.openhealth.utils;

import ieee_11073.part_10101.Nomenclature;
import ieee_11073.part_20601.asn1.AbsoluteTime;
import ieee_11073.part_20601.asn1.AttrValMap;
import ieee_11073.part_20601.asn1.BasicNuObsValue;
import ieee_11073.part_20601.asn1.MetricSpecSmall;
import ieee_11073.part_20601.asn1.OID_Type;
import ieee_11073.part_20601.asn1.TYPE;

public class DIM_Tools {

	public static String getAttributeName (int attrId){
		switch (attrId){
		case Nomenclature.MDC_ATTR_ID_HANDLE : return "Handle";
		case Nomenclature.MDC_ATTR_ID_TYPE : return "Type";
		case Nomenclature.MDC_ATTR_ID_MODEL : return "System-Model";
		case Nomenclature.MDC_ATTR_SYS_ID : return "System-Id";
		case Nomenclature.MDC_ATTR_TIME_STAMP_ABS : return "Absolute-Time-Stamp";
		case Nomenclature.MDC_ATTR_UNIT_CODE: return "Unit-Code";
		case Nomenclature.MDC_ATTR_METRIC_SPEC_SMALL: return "Metric-Spec-Small";
		case Nomenclature.MDC_ATTR_NU_VAL_OBS_BASIC : return "Basic-Nu-Observed-Value";
		case Nomenclature.MDC_ATTR_ATTRIBUTE_VAL_MAP: return "Attribute-Value-Map";
		case Nomenclature.MDC_ATTR_DEV_CONFIG_ID: return "Dev-Configuration-Id";
		case Nomenclature.MDC_ATTR_SYS_TYPE_SPEC_LIST: return "System-Type-Spec-List";
		default: return null;
		}
	}
	
	public static Class getAttributeClass (int attrId){
		switch (attrId){
		case Nomenclature.MDC_ATTR_ID_TYPE : return TYPE.class;
		case Nomenclature.MDC_ATTR_TIME_STAMP_ABS : return AbsoluteTime.class;
		case Nomenclature.MDC_ATTR_UNIT_CODE: return OID_Type.class;
		case Nomenclature.MDC_ATTR_METRIC_SPEC_SMALL: return MetricSpecSmall.class;
		case Nomenclature.MDC_ATTR_NU_VAL_OBS_BASIC : return BasicNuObsValue.class;
		case Nomenclature.MDC_ATTR_ATTRIBUTE_VAL_MAP: return AttrValMap.class;
		default: return null;
		}
	}
}
