package diff.rest.unit.test;

import org.junit.Assert; 
import org.junit.Test;

import diff.rest.DiffResponse;
import diff.rest.EncodedRevision;
import diff.rest.proxy.ControllerImpl;

public class ControllerImplTest {

	String id="123";
	ControllerImpl controller;
	public ControllerImplTest() {
		controller = ControllerImpl.getInstance();
	}

	@Test
	public void testGetInstance() {
		ControllerImpl controllerImpl1 = ControllerImpl.getInstance(); 
		ControllerImpl controllerImpl2 = ControllerImpl.getInstance();
		Assert.assertEquals(controllerImpl1.hashCode()==controllerImpl2.hashCode(),true);
		
	}

	public void testCreateDocumentAndRevisionRight() {
		EncodedRevision right = new EncodedRevision();
		//Attempt to attach revision in doc with nulled content
		Assert.assertEquals(controller.createDocumentAndRevisionRight(id, right.getValue()),false);
		
		//Attempt to attach revision in doc with empty content
		right.setValue("");
		Assert.assertEquals(controller.createDocumentAndRevisionRight(id, right.getValue()),false);
		
		//Attempt to attach revision in doc with valid content
		right.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Assert.assertEquals(controller.createDocumentAndRevisionRight(id, right.getValue()),true);
		
	}

	
	public void testCreateDocumentAndRevisionLeft() {
		EncodedRevision left = new EncodedRevision();

		//Attempt to attach revision in doc with nulled content
		Assert.assertEquals(controller.createDocumentAndRevisionLeft(id, left.getValue()),false);
		
		//Attempt to attach revision in doc with empty content
		left.setValue("");
		Assert.assertEquals(controller.createDocumentAndRevisionLeft(id, left.getValue()),false);
		
		//Attempt to attach revision in doc with valid content
		left.setValue("VGVzdGluZyBpZiBkaWZmZXJlbnQgYnl0ZXMgYXJlIGtub3du");
		Assert.assertEquals(controller.createDocumentAndRevisionLeft(id, left.getValue()),true);
		
	}

	@Test
	public void testViewDiff() {
		
		//Perform diff without data uploaded
		DiffResponse diffResponse = controller.viewDiff(id);
		Assert.assertEquals(diffResponse.getResult().contains("No valid data was uploaded to be compared"),true);
		
		//Create document and revisions
		testCreateDocumentAndRevisionLeft();
		testCreateDocumentAndRevisionRight();
		
		//Perform diff with data uploaded
		diffResponse = controller.viewDiff(id);
		Assert.assertEquals(diffResponse.getResult().contains("Documents are identical"),true);
	}
	
	@Test
	public void testRemoveDiff() {
		
		//Attempt to remove diff document without document created 
		Assert.assertEquals(controller.removeDiff(id),false);
		
		//Create document and revisions
		testCreateDocumentAndRevisionLeft();
		testCreateDocumentAndRevisionRight();
		
		//Attempt to remove diff document with document created 
		Assert.assertEquals(controller.removeDiff(id),true);
	}

}
