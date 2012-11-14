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
    @ASN1Sequence ( name = "ScanReportInfoMPFixed", isSet = false )
    public class ScanReportInfoMPFixed implements IASN1PreparedElement {
            
        @ASN1Element ( name = "data-req-id", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 0  )
    
	private DataReqId data_req_id = null;
                
  @ASN1Integer( name = "" )
    @ASN1ValueRangeConstraint ( 
		
		min = 0L, 
		
		max = 65535L 
		
	   )
	   
        @ASN1Element ( name = "scan-report-no", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 1  )
    
	private Integer scan_report_no = null;
                
  
@ASN1SequenceOf( name = "", isSetOf = false ) 

    
        @ASN1Element ( name = "scan-per-fixed", isOptional =  false , hasTag =  false  , hasDefaultValue =  false, hasExplicitOrder = true, declarationOrder = 2  )
    
	private java.util.Collection<ScanReportPerFixed>  scan_per_fixed = null;
                
  
        
        public DataReqId getData_req_id () {
            return this.data_req_id;
        }

        

        public void setData_req_id (DataReqId value) {
            this.data_req_id = value;
        }
        
  
        
        public Integer getScan_report_no () {
            return this.scan_report_no;
        }

        

        public void setScan_report_no (Integer value) {
            this.scan_report_no = value;
        }
        
  
        
        public java.util.Collection<ScanReportPerFixed>  getScan_per_fixed () {
            return this.scan_per_fixed;
        }

        

        public void setScan_per_fixed (java.util.Collection<ScanReportPerFixed>  value) {
            this.scan_per_fixed = value;
        }
        
  
                    
        
        public void initWithDefaults() {
            
        }

        private static IASN1PreparedElementData preparedData = CoderFactory.getInstance().newPreparedElementData(ScanReportInfoMPFixed.class);
        public IASN1PreparedElementData getPreparedData() {
            return preparedData;
        }

            
    }
            