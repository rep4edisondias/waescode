package diff.rest.factory;

import java.io.UnsupportedEncodingException;

/**
 * Interface used for RevisionFactory. 
 * 
 * @author Dias, Edison
 */
public interface Revision {
	public String getOriginalValue();
	public String getDecodedValue();
	public void Decode();
	public byte[] getDecodedBytes() throws UnsupportedEncodingException;
}
