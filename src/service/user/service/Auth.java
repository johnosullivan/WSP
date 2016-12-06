package service.user.service;

import java.net.UnknownHostException;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import service.user.representation.Account;
import service.user.representation.LoginRepresentation;
import service.user.representation.RegistrationRepresentation;
import service.user.workflow.UserWorkflow;




@Path("/auth/")
public class Auth {

	
	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/registration")
	public Response registration(RegistrationRepresentation req) throws UnknownHostException {
		try {
			System.out.println("Registration: " + req.getUsername() + " - " + req.getPassword());
			
			UserWorkflow activity = new UserWorkflow();
			Account acc = activity.register(req);
			
			return Response.ok(acc).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}
	
	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/login")
	public Response login(LoginRepresentation req) throws UnknownHostException {
		try {
			System.out.println("Login: " + req.getUsername() + " - " + req.getPassword());
			
			UserWorkflow activity = new UserWorkflow();
			Account acc = activity.login(req);
			
			return Response.ok(acc).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}
	
	
}
