package es.libresoft.openhealth;

import java.io.IOException;

import ieee_11073.part_20601.phd.channel.InitializedException;

public class ManagerStarterTCP {

public static TCPChannel tcpChannel;

public static void main(String [] args){
tcpChannel=new TCPChannel();
tcpChannel.start();

System.out.print("Manager Started");

}
}
