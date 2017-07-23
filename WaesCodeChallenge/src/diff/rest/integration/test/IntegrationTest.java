package diff.rest.integration.test;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Assert; 
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import diff.rest.DiffResponse;

public class IntegrationTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		
		/* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
		*                   INTEGRATION TEST 
		* Ensure server is started if not connection will be refused and test will fail
		* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
		*/
				
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());	
		
		String id = "123";
		String params = "Testing if different bytes are known";
		
		byte[] byteArray = params.getBytes("UTF-8"); 
		
		String docBase64String = 
				org.apache.commons.codec.binary.StringUtils.newStringUtf8(org.apache.
				commons.codec.binary.Base64.encodeBase64(byteArray));
		
		
		String jsonString = "";
		jsonString += "{";
		jsonString += "\"value\":\"" + docBase64String + "\"";
		jsonString += "}";
		
		String result;
		Response response;
		
	    Entity<String> data = Entity.entity(jsonString, MediaType.APPLICATION_JSON);
		
		//Create Right Revision and Document - Expects 201 Created
		response = target.path("diff").path(id).path("right").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(data, Response.class);
		Assert.assertEquals(response.getStatus()==201,true);
		result = target.path("diff").path(id).path("right").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(data, String.class);
		Assert.assertEquals(result.contains("Document 123 Revision Uploaded to Right with Success"),true);
		
		//Create Left Revision and Document - Expects 201 Created		
		response = target.path("diff").path(id).path("left").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(data, Response.class);
		Assert.assertEquals(response.getStatus()==201,true);
		result = target.path("diff").path(id).path("left").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(data, String.class);
		Assert.assertEquals(result.contains("Document 123 Revision Uploaded to Left with Success"),true);
		
		//View Diff with revisions already uploaded - Expects 200 OK
		response = target.path("diff").path(id).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		Assert.assertEquals(response.getStatus()==200,true);
		DiffResponse diffResponse = target.path("diff").path(id).request().accept(MediaType.APPLICATION_JSON).get(DiffResponse.class);
		Assert.assertEquals(diffResponse.getResult().contains("Documents are identical"),true);
		
		//Remove Diff document with revisions already created - Expects 200 OK
		response = target.path("diff").path(id).request().accept(MediaType.APPLICATION_JSON).delete(Response.class);
		Assert.assertEquals(response.getStatus()==200,true);		
		
	}
	
	private static URI getBaseURI(){
		return UriBuilder.fromUri("http://localhost:8081/v1").build();
	}

}
