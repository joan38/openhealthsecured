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
package ieee_11073.part_20601.phd.dim.manager;

import java.util.Hashtable;

import ieee_11073.part_20601.phd.dim.Attribute;
import ieee_11073.part_20601.phd.dim.EpiCfgScanner;
import ieee_11073.part_20601.phd.dim.InvalidAttributeException;

public class MEpiCfgScanner extends EpiCfgScanner {

	public MEpiCfgScanner(Hashtable<Integer, Attribute> attributeList)
			throws InvalidAttributeException {
		super(attributeList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void checkAttributes(
			Hashtable<Integer, Attribute> attributes)
			throws InvalidAttributeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Unbuf_Scan_Report_Fixed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Unbuf_Scan_Report_Grouped() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Unbuf_Scan_Report_MP_Fixed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Unbuf_Scan_Report_MP_Grouped() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Unbuf_Scan_Report_MP_Var() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Unbuf_Scan_Report_Var() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SET() {
		// TODO Auto-generated method stub

	}

}
