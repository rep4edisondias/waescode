package diff.rest.unit.test;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import diff.rest.factory.DecodedRevision;

public class DecodedRevisionTest {

	@Test
	public void testDecode() {
		DecodedRevision decodedRevision = new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Assert.assertEquals(decodedRevision.getDecodedValue().contains("Testing if different bytes are known"),true);
	}

	@Test
	public void testGetDecodedBytes() throws UnsupportedEncodingException {
		
		String testString = "Testing if different bytes are known";
		byte[] testByteArray = testString.getBytes("UTF-8");
		DecodedRevision decodedRevision = new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		
		Assert.assertEquals(decodedRevision.getDecodedBytes().length==testByteArray.length,true);
	}

}
