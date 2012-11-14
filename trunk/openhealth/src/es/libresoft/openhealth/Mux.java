package es.libresoft.openhealth;
import java.io.*;


public class Mux extends FilterOutputStream{
	private static final int SOF=0xbf;
	private static final int LINK=0x01;
	private static final int nLINK=0xFE;
	private int bytesLeft=0;
	private int[] buffer = new int[4];
	    
public Mux (OutputStream out) throws IOException {
    	    super(out);
}    
    
public void write(int b) throws IOException{
	
switch (bytesLeft) {
	case  0 : { buffer[0]=b; bytesLeft=-1;} break;
	case -1 : { buffer[1]=b; bytesLeft=-2;} break;
	case -2 : { buffer[2]=b; bytesLeft=-3;} break;
	case -3 : {
		buffer[3]=b;
		bytesLeft= buffer[2]*256+buffer[3];
		super.write(SOF);
		super.write(LINK);
		super.write(((bytesLeft+4)/256)%4);
		super.write((bytesLeft+4)%256);
		super.write(buffer[0]);
		super.write(buffer[1]);
		super.write(buffer[2]);
		super.write(buffer[3]);
	}	break;
	default : {bytesLeft--;	super.write(b);} break;	
	}
	
if (bytesLeft==0) super.write(nLINK);
	
}
public void write(byte[] buffer) throws IOException{
write(buffer, 0, buffer.length);
}

public void write(byte[] buffer, int offset, int count) throws IOException{
	for (int i = offset; i < offset+count; i++)
	write((int)buffer[i] & 0xFF);
}

}
