package es.libresoft.openhealth;

import java.io.IOException;

import ieee_11073.part_20601.phd.channel.InitializedException;

public class ManagerStarterSerial {

public static SerialChannel serialChannel;

public static void main(String [] args){
serialChannel=new SerialChannel();
serialChannel.start();

System.out.print("Manager Started");

}
}
