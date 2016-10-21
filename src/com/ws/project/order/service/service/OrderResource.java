package com.ws.project.order.service.service;
import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Response;

import com.ws.project.order.service.representation.OrderRepresentation;
import com.ws.project.order.service.representation.OrderRequest;
import com.ws.project.order.service.workflow.OrderActivity;

@Path("/orderservice/")
public class OrderResource {

	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/order")
	public OrderRepresentation productSearch(OrderRequest orderRequest) throws UnknownHostException {
		System.out.println("POST ORDER");
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.postOrder(orderRequest);
	}
	
}
