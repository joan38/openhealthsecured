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

package sancane_test;
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
    @ASN1Sequence ( name = "Elements", isSet = false )
    public class Elements implements IASN1PreparedElement {
            
        @ASN1Element ( name = "id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false  )
    
	private INT_U8 id = null;
                
  
        @ASN1Element ( name = "desc", isOptional =  false , hasTag =  false  , hasDefaultValue =  false  )
    
	private ShortDesc desc = null;
                
  
        @ASN1Element ( name = "longDesc", isOptional =  false , hasTag =  false  , hasDefaultValue =  false  )
    
	private LongDesc longDesc = null;
                
  
        
        public INT_U8 getId () {
            return this.id;
        }

        

        public void setId (INT_U8 value) {
            this.id = value;
        }
        
  
        
        public ShortDesc getDesc () {
            return this.desc;
        }

        

        public void setDesc (ShortDesc value) {
            this.desc = value;
        }
        
  
        
        public LongDesc getLongDesc () {
            return this.longDesc;
        }

        

        public void setLongDesc (LongDesc value) {
            this.longDesc = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(Elements.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            