package es.libresoft.openhealth;

import es.libresoft.openhealth.chap.ManagerAuthenticator;

public class ManagerStarterTCP {

    public static TCPChannel tcpChannel;

    public static void main(String[] args) {
        ManagerAuthenticator authenticator = new ManagerAuthenticator();
        byte[] sysId = {(byte) 76, (byte) 73, (byte) 66, (byte) 82, (byte) 69, (byte) 95, (byte) 65, (byte) 71, (byte) 69, (byte) 78, (byte) 84, (byte) 95, (byte) 83, (byte) 84, (byte) 68};
        authenticator.addAgent(sysId, "toto".getBytes());
        tcpChannel = new TCPChannel(authenticator);
        tcpChannel.start();

        System.out.print("Manager Started");
    }
}
