package diff.rest.unit.test;

import org.junit.Assert;

import javax.ws.rs.core.Response;

import org.junit.Test;

import diff.rest.DiffResponse;
import diff.rest.EncodedRevision;
import diff.rest.RestService;

public class RestServiceTest {

	RestService restService;
	public RestServiceTest() {
		restService = new RestService();
	}

	@Test
	public void testViewDiff() {
		
		String id = "123";
		
		EncodedRevision revisionRight = new EncodedRevision();
		revisionRight.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Response response = restService.uploadRevisionRight(id,revisionRight);
		
		String result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 123 Revision Uploaded to Right with Success"),true);
		
		EncodedRevision revisionLeft = new EncodedRevision();
		revisionLeft.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		response = restService.uploadRevisionLeft(id,revisionLeft);
		result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 123 Revision Uploaded to Left with Success"),true);
		
		response =  restService.viewDiff(id);
		DiffResponse diffResponse = (DiffResponse)response.getEntity();
		Assert.assertEquals(diffResponse.getResult().contains("Documents are identical"),true);
	}
	
	@Test
	public void testViewDiffDataNotEncoded() {
		
		String id = "456";
		
		EncodedRevision revisionRight = new EncodedRevision();
		revisionRight.setValue("Value Not Encoded");
		Response response = restService.uploadRevisionRight(id,revisionRight);
		
		String result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 456 Revision Uploaded to Right with Success"),true);
			
		EncodedRevision revisionLeft = new EncodedRevision();
		revisionLeft.setValue("Value Not Encoded");
		response = restService.uploadRevisionLeft(id,revisionLeft);
		
		result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 456 Revision Uploaded to Left with Success"),true);
		
		response =  restService.viewDiff(id);
		DiffResponse diffResponse = (DiffResponse)response.getEntity();
		Assert.assertEquals(diffResponse.getResult().contains("Documents are identical"),true);
	}


	@Test
	public void testUploadRevisionRight() {
		String id = "100";
		EncodedRevision revision = new EncodedRevision();
		revision.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Response response = restService.uploadRevisionRight(id,revision);
		String result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 100 Revision Uploaded to Right with Success"),true);
	}

	@Test
	public void testUploadRevisionLeft() {
		String id = "200";
		EncodedRevision revision = new EncodedRevision();
		revision.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Response response = restService.uploadRevisionLeft(id, revision);
		String result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 200 Revision Uploaded to Left with Success"),true);
	}
	
	@Test
	public void testRemoveDiff() {
		//Add at least one revision (this will create the document) 
		String id = "300";
		EncodedRevision revision = new EncodedRevision();
		revision.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		restService.uploadRevisionLeft(id, revision);
		
		Response response = restService.removeDiff(id);
		String result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 300 removed with success!"),true);
		
		//Try to remove a diff when no document is created
		response = restService.removeDiff(id);
		result = (String)response.getEntity();
		Assert.assertEquals(result.contains("Document 300 was not removed!"),true);
		
	}

}
