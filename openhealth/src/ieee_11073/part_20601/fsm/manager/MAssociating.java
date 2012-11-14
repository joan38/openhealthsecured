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
package ieee_11073.part_20601.fsm.manager;

import java.util.Iterator;

import es.libresoft.openhealth.ManagerConfig;
import es.libresoft.openhealth.events.Event;
import es.libresoft.openhealth.utils.ASN1_Values;
import ieee_11073.part_20601.asn1.AareApdu;
import ieee_11073.part_20601.asn1.AarqApdu;
import ieee_11073.part_20601.asn1.ApduType;
import ieee_11073.part_20601.asn1.AssociateResult;
import ieee_11073.part_20601.asn1.DataProto;
import ieee_11073.part_20601.asn1.DataProtoId;
import ieee_11073.part_20601.asn1.DataProtoList;
import ieee_11073.part_20601.fsm.Associating;
import ieee_11073.part_20601.fsm.StateHandler;

public final class MAssociating extends Associating {

	public MAssociating(StateHandler handler) {
		super(handler);
		System.out.println("State: Associating");
	}

	@Override
	public synchronized void process(ApduType apdu) {
		System.out.println("Associating process ");
	}

	@Override
	public synchronized void processEvent(Event event) {
		System.out.println("Associating process events");
	}

	//----------------------------------PRIVATE--------------------------------------------------------
	
	
}
