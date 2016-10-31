package service.order.service;
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
//import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response;

import service.customer.representation.CustomerRepresentation;
import service.order.representation.OrderCustomerRepresentation;
import service.order.representation.OrderPartnerRepresentation;
import service.order.representation.OrderPartnerStatusRequest;
import service.order.representation.OrderRepresentation;
import service.order.representation.OrderRequest;
import service.order.representation.OrderStatusRepresentation;
import service.order.workflow.OrderActivity;


@Path("/orderservice/")
public class OrderResource {
	/*
	 * GET /order/{orderId}
	 * Gets the order with the id.
	 */
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/order/{orderId}")
	public OrderRepresentation getOrder(@PathParam("orderId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity(); 
		return activity.getOrder(id);
	}
	/*
	 * DELETE /order/{orderId}
	 * This is to cancel on order with order id.
	 */
	@DELETE
	@Produces({"application/json" , "application/xml"})
	@Path("/order/{orderId}")
	public OrderRepresentation cancelOrder(@PathParam("orderId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Cancel) :" + id);
		OrderActivity activity = new OrderActivity(); 
		return activity.cancelOrder(id);
	}
	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/order")
	public OrderRepresentation orderPost(OrderRequest orderRequest) throws UnknownHostException {
		System.out.println("POST ORDER");
		OrderActivity activity = new OrderActivity();
		return activity.postOrder(orderRequest);
	}
	
	
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/order/status/{orderId}")
	public OrderStatusRepresentation getOrderStatus(@PathParam("orderId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity(); 
		return activity.getOrderStatus(id);
	}
	
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/orders/partner/{partnerId}")
	public OrderPartnerRepresentation getPartnerOrders(@PathParam("partnerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity(); 
		return activity.getOrdersPartner(id);
	}
	
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/orders/customer/{customerId}")
	public OrderCustomerRepresentation getCustomerOrders(@PathParam("customerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity(); 
		return activity.getOrdersCustomer(id);
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order/partner")
	public Response orderUpdatePartner(OrderPartnerStatusRequest req) throws UnknownHostException {
		OrderActivity activity = new OrderActivity(); 
		boolean status = activity.updateStatus(req);
		System.out.println("PUT STATUS :" + status);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order/customer")
	public Response orderUpdateCustomer(CustomerRepresentation customerRequest) throws UnknownHostException {
		
		return Response.ok().build();
	}
	
	
	
	
	
	
	
	
}
