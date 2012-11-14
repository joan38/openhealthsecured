package agent.serial;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.*;

public class AgentSerial {
public static SerialPort serialPort;
private static String portName = "COM2";
	
	
public static void main (String[] args) throws Exception{
	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
	
	serialPort = (SerialPort) portIdentifier.open("RTBug_network",2000);
	serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
	SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

	InputStream in=serialPort.getInputStream();
	OutputStream out=serialPort.getOutputStream();
	
	String M1="RESET\r";
	String M2="AGENT SETTIME 2011-10-17T08:30:0000\r";
	String M3="AGENT CALL 00:07:80:44:22:C1 1004\r";
	String M4="AGENT UPDATE ffff 1 a4c 99e0\r";
	String M5="AGENT UPDATE\r";
	String M6="AGENT DISASSOCIATE\r";
	String M7="HDP DELETE 1\r";
	String M8="CLOSE 0\r";
	
	out.write(M1.getBytes());
	Thread.sleep(500);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M2.getBytes());
	Thread.sleep(500);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M3.getBytes());
	Thread.sleep(5000);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M4.getBytes());
	Thread.sleep(500);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M5.getBytes());
	Thread.sleep(5000);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M6.getBytes());
	Thread.sleep(500);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M7.getBytes());
	Thread.sleep(500);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M8.getBytes());
	Thread.sleep(2000);
	while (in.available()>0) 
	System.out.print((char)in.read());
	System.out.println();
	
	out.write(M1.getBytes());
	Thread.sleep(500);
	
	serialPort.close();
		
	}
	
	

}
