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
    @ASN1Sequence ( name = "AbsTimeRange", isSet = false )
    public class AbsTimeRange implements IASN1PreparedElement {
            
        @ASN1Element ( name = "from-time", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private AbsoluteTime from_time = null;
                
  
        @ASN1Element ( name = "to-time", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private AbsoluteTime to_time = null;
                
  
        
        public AbsoluteTime getFrom_time () {
            return this.from_time;
        }

        

        public void setFrom_time (AbsoluteTime value) {
            this.from_time = value;
        }
        
  
        
        public AbsoluteTime getTo_time () {
            return this.to_time;
        }

        

        public void setTo_time (AbsoluteTime value) {
            this.to_time = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(AbsTimeRange.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            