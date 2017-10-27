package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/recive")
public class ReciveFromServer {
	
	static String  RecievedMessage;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "the message has been recived\t(" + this.RecievedMessage + ")";
	}

	
	    @POST
		@Path("/post")
	    @Consumes({"text/html"})
	    public String createProductInJSON(String message) {
	    	ReciveFromServer.RecievedMessage = message;
			String result = "The Message ("+ message + ") Has been Recived You can "
					+ "see it by following this Link http://localhost:8040/myEndPointApp/rest/recive"  ;
			return result;

		}
	
}
