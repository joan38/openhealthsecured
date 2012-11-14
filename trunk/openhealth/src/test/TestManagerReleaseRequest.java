package test;

import ieee_11073.part_20601.asn1.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.types.BitString;


public class TestManagerReleaseRequest {
	static final byte[] HEX_CHAR_TABLE = {
	    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
	    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
	    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
	    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
	  }; 

	  public static String getHexString(byte[] raw) 
	    throws UnsupportedEncodingException 
	  {
	    byte[] hex = new byte[2 * raw.length];
	    int index = 0;

	    for (byte b : raw) {
	      int v = b & 0xFF;
	      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
	      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
	    }
	    return new String(hex, "ASCII");
	  }
	  
	  public static void main(String[] args) {			

			try {
				
				/* TEST ApduType choice */
				/* Make ApduType */
				ApduType at = new ApduType();
				
				/* Make ApduType -> RlrqApdu */
				RlrqApdu rlrq = new RlrqApdu();
				
				/* Make ApduType -> RlrqApdu -> ReleaseRequesReason Normal(0) NoMoreConfs(1) ConfChanges(2) */ 
				ReleaseRequestReason rrr = new ReleaseRequestReason(0);
				
				/* Add ReleaseRequestReason to ApduType -> AarqApdu -> ReleaseRequestReason */
				rlrq.setReason(rrr);
				
				/* Add RlrqApdu to ApduType -> RlrqApdu */
				at.selectRlrq(rlrq);
								
				//Coding the data on the net:
				IEncoder<ApduType> encoder = CoderFactory.getInstance().newEncoder("MDER");
				IDecoder decoder = CoderFactory.getInstance().newDecoder("MDER");
				
				System.out.println("Conectando con el servidor...");
				Socket s = new Socket("127.0.0.1",9999);
				
				encoder.encode(at, s.getOutputStream());
				ApduType apdu = decoder.decode(s.getInputStream(), ApduType.class);
				if (apdu.isAbrtSelected()){
					System.out.println("Initializing ...");
					AbrtApdu abrt = apdu.getAbrt();
					System.out.println("AbortReason: "+ abrt.getReason().getValue());
					int reason = abrt.getReason().getValue();					
					
					if (reason == 0){
						System.out.println("Undefined");						
					}else if (reason == 1){
						System.out.println("Buffer overflow");						
					}else if (reason == 2){
						System.out.println("Response timeout");
					}else if (reason == 3){
						System.out.println("Configuration timeout");
					}else {
						System.out.println("Unknown Message");						
					}					
				}else System.out.println("Problem!");
			}catch (Exception e){
				System.out.println("Error!");
				e.printStackTrace();
			}			
	  }	
}
