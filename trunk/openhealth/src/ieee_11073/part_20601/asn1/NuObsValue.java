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
    @ASN1Sequence ( name = "NuObsValue", isSet = false )
    public class NuObsValue implements IASN1PreparedElement {
            
        @ASN1Element ( name = "metric-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private OID_Type metric_id = null;
                
  
        @ASN1Element ( name = "state", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private MeasurementStatus state = null;
                
  
        @ASN1Element ( name = "unit-code", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 2  )
    
	private OID_Type unit_code = null;
                
  
        @ASN1Element ( name = "value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 3  )
    
	private FLOAT_Type value = null;
                
  
        
        public OID_Type getMetric_id () {
            return this.metric_id;
        }

        

        public void setMetric_id (OID_Type value) {
            this.metric_id = value;
        }
        
  
        
        public MeasurementStatus getState () {
            return this.state;
        }

        

        public void setState (MeasurementStatus value) {
            this.state = value;
        }
        
  
        
        public OID_Type getUnit_code () {
            return this.unit_code;
        }

        

        public void setUnit_code (OID_Type value) {
            this.unit_code = value;
        }
        
  
        
        public FLOAT_Type getValue () {
            return this.value;
        }

        

        public void setValue (FLOAT_Type value) {
            this.value = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(NuObsValue.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            