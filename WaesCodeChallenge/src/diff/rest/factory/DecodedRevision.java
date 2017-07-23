package diff.rest.factory;

import java.io.UnsupportedEncodingException;

/**
 * This is class is responsible to receive the EncodedRevision value and keep the decoded value.
 * 
 * @author Dias, Edison
 */
public class DecodedRevision implements Revision{

	private String originalValue;
	private String decodedValue;
	/*
	 * The Constructor receive the encoded value and perform Decode() to generate the decoded value
	 */
	public DecodedRevision(String originalValue) {
		this.originalValue = originalValue;
		Decode();
	}
	public String getOriginalValue() {
		return originalValue;
	}
	public String getDecodedValue() {
		return decodedValue;
	}
	/* 
	 * This method takes a encoded base64 String uploaded in revisions sides and decode it
	 */
	public void Decode(){
		this.decodedValue = org.apache.commons.codec.binary.StringUtils.newStringUtf8(org.apache.
				commons.codec.binary.Base64.decodeBase64(this.originalValue));
	}
	/* 
	 * This method return an byte array of the decoded value
	 */
	public byte[] getDecodedBytes() throws UnsupportedEncodingException{
		return this.getDecodedValue().getBytes("UTF-8");
	}
}
