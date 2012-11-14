package es.libresoft.openhealth;
import java.io.*;

public class DeMux extends FilterInputStream{
	private static final int SOF=0xbf;
	private static final int LINK=0x01;
	private long bytesLeft=0;
	    
public DeMux (InputStream in) throws IOException {
    	    super(in);
}    
    
public int read() throws IOException{
	while (true) {
	if (bytesLeft>0) {
		bytesLeft--;
		return super.read();}
	else {checkSOF();}
	}
}
public int read(byte[] buffer) throws IOException{
return read(buffer, 0, buffer.length);
}

public int read(byte[] buffer, int offset, int count) throws IOException{
	for (int i = 0; i < offset; i++)
	read();
	for (int i = 0; i < count; i++)
	buffer[i]=(byte)read();
	return count;
}

public long skip (long byteCount) throws IOException{
	bytesLeft-=	byteCount;
	return super.skip(byteCount);
}
    
private void checkSOF() throws IOException{
	if (super.read()==SOF)
		if (super.read()==LINK) {
			bytesLeft = in.read();
			bytesLeft = (bytesLeft % 4)*256 + in.read();
		}		
	}
}
