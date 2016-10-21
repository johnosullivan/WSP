package com.ws.project.customer.service.service;

import java.net.UnknownHostException;
import javax.jws.WebParam;
//import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
import javax.ws.rs.PathParam;

import com.ws.project.customer.service.representation.CustomerRepresentation;
import com.ws.project.customer.service.representation.CustomerRequest;
import com.ws.project.customer.service.workflow.CustomerActivity;

@Path("/customerservice/")
public class CustomerResource {

	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/customer/{customerId}")
	public CustomerRepresentation getCustomer(@PathParam("customerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (CUSTOMER) :" + id);
		CustomerActivity cusActivity = new CustomerActivity(); 
		return cusActivity.getCustomer(id);
	}
	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/customer")
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest) throws UnknownHostException {
		System.out.println("POST METHOD");
		CustomerActivity cusActivity = new CustomerActivity();
		return cusActivity.createCustomer(customerRequest);
	}
	
	@PUT
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/customer")
	public CustomerRepresentation updateCustomer(CustomerRepresentation  customerRequest) throws UnknownHostException {
		System.out.println("PUT METHOD");
		CustomerActivity cusActivity = new CustomerActivity();
		return cusActivity.updateCustomer(customerRequest);
	}
	
	@DELETE
	@Produces({"application/json" , "application/xml"})
	@Path("/customer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") String id) throws UnknownHostException {
		System.out.println("DELETE METHOD: " + id);
		CustomerActivity cusActivity = new CustomerActivity();
		if (cusActivity.deleteCustomer(id)) {
			return Response.ok().build();
		}
		return Response.ok().build();
	}
	
			
}
