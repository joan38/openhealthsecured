package agent.thermometer;

import ieee_11073.part_20601.asn1.ApduType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;
import org.bn.CoderFactory;
import org.bn.IEncoder;

public class SenderThread extends Thread {

    private static Vector<ApduType> sendQeue = new Vector<ApduType>(50);
    private static Socket socket;

    public SenderThread(Socket s) {
        socket = s;
    }

    public static void sendApdu(ApduType apdu) {
        sendQeue.add(apdu);
        System.out.println("sendQeue has " + sendQeue.size() + "APDU`s");
    }

    private boolean checkSocketState() {
        if (socket.isConnected() && !socket.isClosed()) {
            return true;
        }
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Disconnected");
        System.exit(0);
        return false;
    }

    public void run() {
        try {
            IEncoder<ApduType> encoder = CoderFactory.getInstance().newEncoder(ReceiverThread.ENCODING_RULE);
            while (true) {
                while (sendQeue.isEmpty()) {
                    Thread.sleep(50);
                }
                ApduType apdu = sendQeue.remove(0);
                checkSocketState();
                encoder.encode(apdu, socket.getOutputStream());
                OutputStream debug = new ByteArrayOutputStream();
                encoder.encode(apdu, debug);
                byte[] ba = ((ByteArrayOutputStream) debug).toByteArray();
                System.out.print("Sended Bytes" + ReceiverThread.getHexString(ba));

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
