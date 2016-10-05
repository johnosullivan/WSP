package com.ws.project.service.service;

import java.net.UnknownHostException;

import javax.jws.WebParam;
import javax.jws.WebService;
//import java.util.Set;
//import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ws.project.service.workflow.CustomerActivity;

import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
import com.ws.project.service.representation.CustomerRepresentation;

@WebService(targetNamespace = "http://service.service.project.ws.com/", portName = "CustomerResourcePort", serviceName = "CustomerResourceService")
@Path("/customerservice/")
public class CustomerResource {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public CustomerRepresentation getCustomer(@PathParam("customerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (CUSTOMER) :" + id);
		CustomerActivity cusActivity = new CustomerActivity();
		return cusActivity.getCustomer(id);
	}
		
}
