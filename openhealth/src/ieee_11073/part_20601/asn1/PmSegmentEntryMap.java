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
    @ASN1Sequence ( name = "PmSegmentEntryMap", isSet = false )
    public class PmSegmentEntryMap implements IASN1PreparedElement {
            
        @ASN1Element ( name = "segm-entry-header", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private SegmEntryHeader segm_entry_header = null;
                
  
        @ASN1Element ( name = "segm-entry-elem-list", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private SegmEntryElemList segm_entry_elem_list = null;
                
  
        
        public SegmEntryHeader getSegm_entry_header () {
            return this.segm_entry_header;
        }

        

        public void setSegm_entry_header (SegmEntryHeader value) {
            this.segm_entry_header = value;
        }
        
  
        
        public SegmEntryElemList getSegm_entry_elem_list () {
            return this.segm_entry_elem_list;
        }

        

        public void setSegm_entry_elem_list (SegmEntryElemList value) {
            this.segm_entry_elem_list = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(PmSegmentEntryMap.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            