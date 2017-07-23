package diff.rest.unit.test;

import org.junit.Assert; 
import org.junit.Test;

import diff.rest.factory.DecodedRevision;
import diff.rest.proxy.Document;

/**
 * @author Dias, Edison
 * This is responsible to test Document class, main method is compare(), that will evaluate 2 document revisions and look for differences.
 */
public class DocumentTest {

	/**
	 * Test method that will exercise all the potential responses for the document.compare() method.
	 * Some Base64 values were generated based in String values. 
	 */
	@Test
	public void testCompare() {
		
		Document doc = new Document("123");
		/*
		 * No valid revisions added
		 */
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded to be compared"), true);
		
		doc.setRevisionLeft(null);
		doc.setRevisionRight(new DecodedRevision(null));
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded to be compared"), true);
		
		doc.setRevisionLeft(new DecodedRevision(null));
		doc.setRevisionRight(null);
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded to be compared"), true);
		
		doc.setRevisionLeft(new DecodedRevision(null));
		doc.setRevisionRight(new DecodedRevision(null));
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded to be compared"), true);
		
		/*
		 * No valid revisions in one side  
		 */
		doc.setRevisionLeft(new DecodedRevision(null));
		doc.setRevisionRight(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded on Left"), true);
		
		doc.setRevisionLeft(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		doc.setRevisionRight(new DecodedRevision(null));
		
		Assert.assertEquals(doc.compare().getResult().contains("No valid data was uploaded on Right"), true);
		
		/*
		 * Different Sizes
		 */
		
		doc.setRevisionLeft(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		doc.setRevisionRight(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3duIGFuZCBtb3JlIHRoZXkgaGF2ZSBjb21wbGV0ZWx5IGRpZmZlcmVudCBzaXplcw=="));
		
		Assert.assertEquals(doc.compare().getResult().contains("Uploaded data have different size"), true);
		
		/*
		 * Identical revisions
		 */
		
		doc.setRevisionRight(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		doc.setRevisionLeft(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		
		Assert.assertEquals(doc.compare().getResult().endsWith("Documents are identical"), true);
		
		/*
		 * OffSets identified revisions
		 */
		
		doc.setRevisionRight(new DecodedRevision("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du"));
		doc.setRevisionLeft(new DecodedRevision("VGVzdGluZyBvZiBkaWZmZXJlbnQgYnl0ZXMgbm90IGtub3du"));
		
		Assert.assertEquals(doc.compare().getResult().contains("=OffSet="), true);
		
	}

}
