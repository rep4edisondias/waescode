package diff.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import diff.rest.proxy.Controller;

/**
 * This class exposes all paths and methods on RestApi Server.
 * 
 * HTTP ERROR CODES EXPECTED:
 *	- 200 OK (GET\DELETE)
 * 	- 201 Created (POST) 
 *	- 500 Internal Error (GET\POST\DELETE)
 *		
 * @author Dias, Edison
 */
@Path("/diff/{id}")
public class RestService {

	Controller controller;
	/**
	 * On the class constructor the controller is instantiated.
	 */
	public RestService(){
		controller = Controller.getInstance();
	}
	
	/**
	 * This is the GET method that will perform the Diff between the revisions of 
	 * the document identified in id parameter and return POJO class DiffResponse
	 * @param id
	 * @return 
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response viewDiff(@PathParam("id") String id) {
		
		DiffResponse response = null;
        try{
        	response = controller.viewDiff(id);
        }
        catch(Exception e){
        	Response.status(500).entity(response).build();
        }
        return Response.status(200).entity(response).build();
	}

	/**
	 * This is the POST method for right revision, this receives the document id 
	 * and EncodedRevision POJO object and returns a String value  
	 * 
	 * @param id
	 * @param right
	 * @return
	 */
	@POST
	@Path("/right")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadRevisionRight(@PathParam("id") String id, EncodedRevision right){

		String response = "Id|data uploaded are not valid!";
        try{			
        	if(!id.isEmpty() && controller.createRight(id, right)){
        		response = "Document "+ id + " Revision Uploaded to Right with Success";
        	}else{
        		return Response.status(500).entity(response).build();
        	}
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        	return Response.status(500).entity(response).build();
        }
        
        return Response.status(201).entity(response).build();	
	}
	
	/**
	 * This is the POST method for left revision, this receives the document id 
	 * and EncodedRevision POJO object and returns a String value
	 * 
	 * @param id
	 * @param left
	 * @return
	 */
	@POST
	@Path("/left")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadRevisionLeft(@PathParam("id") String id, EncodedRevision left) {

		String response = "Id|data uploaded are not valid!";
        try{			
        	if(!id.isEmpty() && controller.createLeft(id, left)){
        		response = "Document "+ id + " Revision Uploaded to Left with Success";
        	}else{
        		return Response.status(500).entity(response).build();
        	}
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        	return Response.status(500).entity(response).build();
        }
        
        return Response.status(201).entity(response).build();	
	}
	
	/**
	 * This is the DELETE method that will perform the removal of revision diff based on Document id
	 * @param id
	 * @return 
	 */
	@DELETE
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeDiff(@PathParam("id") String id) {
		
		String response = "Document " + id + " was not removed!";
		try{
        	if (controller.removeDiff(id)){
        		response = "Document " + id + " removed with success!";
        		return Response.status(200).entity(response).build();
        	}
        	return Response.status(500).entity(response).build();
        }
        catch(Exception e){
        	return Response.status(500).entity(response).build();
        }
	}
	
}
