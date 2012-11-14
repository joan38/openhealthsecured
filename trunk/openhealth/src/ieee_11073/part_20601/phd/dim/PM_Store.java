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
package ieee_11073.part_20601.phd.dim;

import java.util.Hashtable;

import ieee_11073.part_10101.Nomenclature;

	/**
	 * An instance of the PM-store class provides long-term storage capabilities for metric 
	 * data. Data are stored in a variable number of PM-segment objects (see 6.3.8). The stored 
	 * data of the PM-store object are requested from the agent by the manager using object 
	 * access services (see 7.3). Anybody not familiar with the PM-store concept may wish to 
	 * read Annex C for a conceptual overview prior to reading the following subclauses.
	 */

public abstract class PM_Store extends DIM implements PM_Store_Events, GET_Service {

	public PM_Store(Hashtable<Integer,Attribute> attributeList) throws InvalidAttributeException {
		super(attributeList);
	}

	public int getNomenclatureCode (){
		return Nomenclature.MDC_MOC_VMO_PMSTORE;
	}
	
	/* PM_Store Object methods */
	
	/**
	 * This method allows the manager to delete the data currently stored in one or more selected 
	 * PM-segments. All entries in the selected PM-segments are deleted. If the agent supports a 
	 * variable number of PM-segments, the agent may delete empty PM-segments. Additionally, the 
	 * agent may clear PM-segments without direction from the manager (e.g., the user of the agent
	 * could choose to delete data stored on the agent); however, if doing so while in an Associated
	 * state, the Instance-Number shall remain empty for the duration of the association. The 
	 * Instance-Number of all other PM-segments shall be unaffected by clearing a segment. If this 
	 * method is invoked on a PM-segment that has the Operational-State attribute set to enabled, 
	 * the agent shall reply with a not-allowed-by-object error (roer) with a return code of 
	 * MDC_RET_CODE_OBJ_BUSY. 
	 * Note that the behavior of the Clear-Segments method is application 
	 * specific. The method may remove all entries from the specified PM-segment, leaving it empty, 
	 * or it may remove the defined PM-segment completely. This behavior is defined in the 
	 * PM-Store-Capab attribute. For specific applications, recommendations are defined in 
	 * corresponding device specializations, making use of the PM-store.
	 */
	public abstract void Clear_Segments ();
	
	/**
	 * This method allows the manager to retrieve PM-segment attributes of one or more PM-segments, 
	 * with the exception of the Fixed-Segment-Data attribute which contains the actual stored data 
	 * and is retrieved by using the Trig-Segment-Data-Xfer method. In particular, the 
	 * Get-Segment-Info method allows the manager to retrieve the Instance-Number attributes of the 
	 * PM-segment object instances and their data contents.
	 */
	public abstract void Get_Segment_Info ();
	
	/**
	 * This method allows the manager to start the transfer of the Fixed-Segment-Data attribute of a
	 * specified PM-segment. The agent indicates in the response if it accepts or denies this request.
	 * If the agent accepts the request, the agent sends Segment-Data-Event messages as described in 
	 * 6.3.7.5. If this method is invoked on a PM-segment that has the Operational-State attribute 
	 * set to enabled, the agent shall reply with a not-allowed-by-object error (roer) with a return 
	 * code of MDC_RET_CODE_OBJ_BUSY.
	 */
	public abstract void Trig_Segment_Data_Xfer ();
}
