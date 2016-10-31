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
	 * GET /order/{orderId} Gets the order with the id.
	 */
	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/order/{orderId}")
	public Response getOrder(@PathParam("orderId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		try {
			System.out.println("GET METHOD (Order) :" + id);
			OrderActivity activity = new OrderActivity();
			OrderRepresentation status = activity.getOrder(id);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /order/{orderId} This is to cancel on order with order id.
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/order/{orderId}")
	public Response cancelOrder(@PathParam("orderId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		try {
		System.out.println("GET METHOD (Cancel) :" + id);
		OrderActivity activity = new OrderActivity();
		OrderRepresentation status = activity.cancelOrder(id);
		return Response.ok(status).build();
	} catch (Exception e) {
		return Response.status(400).build();
	}
	}

	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order")
	public Response orderPost(OrderRequest orderRequest) throws UnknownHostException {
		try {
		System.out.println("POST ORDER");
		OrderActivity activity = new OrderActivity();
		OrderRepresentation status = activity.postOrder(orderRequest);
		return Response.ok(status).build();
	} catch (Exception e) {
		return Response.status(400).build();
	}
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/order/status/{orderId}")
	public Response getOrderStatus(@PathParam("orderId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		try {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity();
		OrderStatusRepresentation status = activity.getOrderStatus(id);
		return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/orders/partner/{partnerId}")
	public Response getPartnerOrders(@PathParam("partnerId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		try {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity();
		OrderPartnerRepresentation status =  activity.getOrdersPartner(id);
		return Response.ok(status).build();
	} catch (Exception e) {
		return Response.status(400).build();
	}
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/orders/customer/{customerId}")
	public Response getCustomerOrders(@PathParam("customerId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		try {
		System.out.println("GET METHOD (Order) :" + id);
		OrderActivity activity = new OrderActivity();
		OrderCustomerRepresentation status = activity.getOrdersCustomer(id);
		return Response.ok(status).build();
	} catch (Exception e) {
		return Response.status(400).build();
	}
	}

	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order/partner")
	public Response orderUpdatePartner(OrderPartnerStatusRequest req) throws UnknownHostException {
		try {
		OrderActivity activity = new OrderActivity();
		boolean status = activity.updateStatus(req);
		System.out.println("PUT STATUS :" + status);
		return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order/customer")
	public Response orderUpdateCustomer(OrderPartnerStatusRequest req) throws UnknownHostException {
		try {
		OrderActivity activity = new OrderActivity();
		boolean status = activity.updateStatusCustomer(req);
		System.out.println("PUT STATUS :" + status);
		return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/order/customer")
	public Response orderUpdateCustomer(CustomerRepresentation customerRequest) throws UnknownHostException {

		return Response.ok().build();
	}

}
