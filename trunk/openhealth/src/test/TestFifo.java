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
package test;

import es.libresoft.openhealth.utils.FIFO;
import ieee_11073.part_20601.phd.channel.VirtualChannel.SenderThread;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class TestFifo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
		FIFO<Integer> list = new FIFO<Integer>();
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(1));
		System.out.println("list: " + list);
		System.out.println("Get first: " + list.remove());
		System.out.println("list: " + list);
		list.clear();
		System.out.println("clear: " + list);
		System.out.println("Get first: " + list.remove());
		System.out.println("list: " + list);
		System.out.println("Get first: " + list.remove());
		System.out.println("list: " + list);
	}

}
