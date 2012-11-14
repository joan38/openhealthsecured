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
    @ASN1Sequence ( name = "AVAX_Type", isSet = false )
    public class AVAX_Type implements IASN1PreparedElement {
            
        @ASN1Element ( name = "attribute-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false  )
    
	private OID_Type attribute_id = null;
                
  @ASN1Any( name = "" )
    
        @ASN1Element ( name = "attribute-value", isOptional =  false , hasTag =  false  , hasDefaultValue =  false  )
    
	private byte[] attribute_value = null;
                
  
        
        public OID_Type getAttribute_id () {
            return this.attribute_id;
        }

        

        public void setAttribute_id (OID_Type value) {
            this.attribute_id = value;
        }
        
  
        
        public byte[] getAttribute_value () {
            return this.attribute_value;
        }

        

        public void setAttribute_value (byte[] value) {
            this.attribute_value = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(AVAX_Type.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            