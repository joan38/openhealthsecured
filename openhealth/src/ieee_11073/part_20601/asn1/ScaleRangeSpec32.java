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
    @ASN1Sequence ( name = "ScaleRangeSpec32", isSet = false )
    public class ScaleRangeSpec32 implements IASN1PreparedElement {
            
        @ASN1Element ( name = "lower-absolute-value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private FLOAT_Type lower_absolute_value = null;
                
  
        @ASN1Element ( name = "upper-absolute-value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private FLOAT_Type upper_absolute_value = null;
                
  
        @ASN1Element ( name = "lower-scaled-value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 2  )
    
	private INT_U32 lower_scaled_value = null;
                
  
        @ASN1Element ( name = "upper-scaled-value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 3  )
    
	private INT_U32 upper_scaled_value = null;
                
  
        
        public FLOAT_Type getLower_absolute_value () {
            return this.lower_absolute_value;
        }

        

        public void setLower_absolute_value (FLOAT_Type value) {
            this.lower_absolute_value = value;
        }
        
  
        
        public FLOAT_Type getUpper_absolute_value () {
            return this.upper_absolute_value;
        }

        

        public void setUpper_absolute_value (FLOAT_Type value) {
            this.upper_absolute_value = value;
        }
        
  
        
        public INT_U32 getLower_scaled_value () {
            return this.lower_scaled_value;
        }

        

        public void setLower_scaled_value (INT_U32 value) {
            this.lower_scaled_value = value;
        }
        
  
        
        public INT_U32 getUpper_scaled_value () {
            return this.upper_scaled_value;
        }

        

        public void setUpper_scaled_value (INT_U32 value) {
            this.upper_scaled_value = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(ScaleRangeSpec32.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            