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
    @ASN1Sequence ( name = "DataRequest", isSet = false )
    public class DataRequest implements IASN1PreparedElement {
            
        @ASN1Element ( name = "data-req-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private DataReqId data_req_id = null;
                
  
        @ASN1Element ( name = "data-req-mode", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private DataReqMode data_req_mode = null;
                
  
        @ASN1Element ( name = "data-req-time", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 2  )
    
	private RelativeTime data_req_time = null;
                
  
        @ASN1Element ( name = "data-req-person-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 3  )
    
	private INT_U16 data_req_person_id = null;
                
  
        @ASN1Element ( name = "data-req-class", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 4  )
    
	private OID_Type data_req_class = null;
                
  
        @ASN1Element ( name = "data-req-obj-handle-list", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 5  )
    
	private HANDLEList data_req_obj_handle_list = null;
                
  
        
        public DataReqId getData_req_id () {
            return this.data_req_id;
        }

        

        public void setData_req_id (DataReqId value) {
            this.data_req_id = value;
        }
        
  
        
        public DataReqMode getData_req_mode () {
            return this.data_req_mode;
        }

        

        public void setData_req_mode (DataReqMode value) {
            this.data_req_mode = value;
        }
        
  
        
        public RelativeTime getData_req_time () {
            return this.data_req_time;
        }

        

        public void setData_req_time (RelativeTime value) {
            this.data_req_time = value;
        }
        
  
        
        public INT_U16 getData_req_person_id () {
            return this.data_req_person_id;
        }

        

        public void setData_req_person_id (INT_U16 value) {
            this.data_req_person_id = value;
        }
        
  
        
        public OID_Type getData_req_class () {
            return this.data_req_class;
        }

        

        public void setData_req_class (OID_Type value) {
            this.data_req_class = value;
        }
        
  
        
        public HANDLEList getData_req_obj_handle_list () {
            return this.data_req_obj_handle_list;
        }

        

        public void setData_req_obj_handle_list (HANDLEList value) {
            this.data_req_obj_handle_list = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(DataRequest.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            