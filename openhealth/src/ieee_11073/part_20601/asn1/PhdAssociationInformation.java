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

package ieee_11073.part_20601.asn1;
//
// This file was generated by the BinaryNotes compiler.
// See http://bnotes.sourceforge.net 
// Any modifications to this file will be lost upon recompilation of the source ASN.1. 
//

import org.bn.*;
import org.bn.annotations.*;
import org.bn.annotations.constraints.*;
import org.bn.coders.*;
import org.bn.types.*;




    @ASN1PreparedElement
    @ASN1Sequence ( name = "PhdAssociationInformation", isSet = false )
    public class PhdAssociationInformation implements IASN1PreparedElement {
            
        @ASN1Element ( name = "protocol-version", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private ProtocolVersion protocol_version = null;
                
  
        @ASN1Element ( name = "encoding-rules", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private EncodingRules encoding_rules = null;
                
  
        @ASN1Element ( name = "nomenclature-version", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 2  )
    
	private NomenclatureVersion nomenclature_version = null;
                
  
        @ASN1Element ( name = "functional-units", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 3  )
    
	private FunctionalUnits functional_units = null;
                
  
        @ASN1Element ( name = "system-type", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 4  )
    
	private SystemType system_type = null;
                
  @ASN1OctetString( name = "" )
    
        @ASN1Element ( name = "system-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 5  )
    
	private byte[] system_id = null;
                
  
        @ASN1Element ( name = "dev-config-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 6  )
    
	private ConfigId dev_config_id = null;
                
  
        @ASN1Element ( name = "data-req-mode-capab", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 7  )
    
	private DataReqModeCapab data_req_mode_capab = null;
                
  
        @ASN1Element ( name = "option-list", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 8  )
    
	private AttributeList option_list = null;
                
  
        
        public ProtocolVersion getProtocol_version () {
            return this.protocol_version;
        }

        

        public void setProtocol_version (ProtocolVersion value) {
            this.protocol_version = value;
        }
        
  
        
        public EncodingRules getEncoding_rules () {
            return this.encoding_rules;
        }

        

        public void setEncoding_rules (EncodingRules value) {
            this.encoding_rules = value;
        }
        
  
        
        public NomenclatureVersion getNomenclature_version () {
            return this.nomenclature_version;
        }

        

        public void setNomenclature_version (NomenclatureVersion value) {
            this.nomenclature_version = value;
        }
        
  
        
        public FunctionalUnits getFunctional_units () {
            return this.functional_units;
        }

        

        public void setFunctional_units (FunctionalUnits value) {
            this.functional_units = value;
        }
        
  
        
        public SystemType getSystem_type () {
            return this.system_type;
        }

        

        public void setSystem_type (SystemType value) {
            this.system_type = value;
        }
        
  
        
        public byte[] getSystem_id () {
            return this.system_id;
        }

        

        public void setSystem_id (byte[] value) {
            this.system_id = value;
        }
        
  
        
        public ConfigId getDev_config_id () {
            return this.dev_config_id;
        }

        

        public void setDev_config_id (ConfigId value) {
            this.dev_config_id = value;
        }
        
  
        
        public DataReqModeCapab getData_req_mode_capab () {
            return this.data_req_mode_capab;
        }

        

        public void setData_req_mode_capab (DataReqModeCapab value) {
            this.data_req_mode_capab = value;
        }
        
  
        
        public AttributeList getOption_list () {
            return this.option_list;
        }

        

        public void setOption_list (AttributeList value) {
            this.option_list = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(PhdAssociationInformation.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            