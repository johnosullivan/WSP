package service.payment.service;

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

import service.payment.representation.PaymentRequest;
import service.payment.representation.PaymentRepresentationInterface;
import service.payment.workflow.PaymentActivity;

@Path("/paymentservice/")
public class PaymentResource {

	/*
	 * POST /payment/customer This will create a new payment object from the
	 * payload.
	 */
	@POST
	@Consumes({  "application/json", "application/xml" })
	@Produces({  "application/json", "application/xml" })
	@Path("/payment/customer")
	public Response createCustomerPayment(PaymentRequest request) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			boolean status = activity.addPaymentCustomer(request);
			System.out.println(status);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /payment/customer This will create a new payment object from the
	 * payload.
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/payment/customer")
	public Response updateCustomerPayment(PaymentRequest request) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			boolean status = activity.updatePaymentCustomer(request);
			System.out.println(status);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * GET /payment/customer/{customerId} This will get the customer payment
	 * with the the customerId
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	@Path("/payment/customer/{customerId}")
	public Response getCustomerPayment(@PathParam("customerId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		PaymentActivity activity = new PaymentActivity();
		try {
			PaymentRepresentationInterface temp = activity.getCustomerPayment(id);
			return Response.ok(temp).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}
	}

	/*
	 * DELETE /payment/customer This will remove the customer's payment info
	 * from online
	 */
	@DELETE
	@Produces({ "application/xml", "application/json" })
	@Path("/payment/customer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") String id) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			if (activity.deleteCustomerPayment(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /payment/partner This will create a new payment object from the
	 * payload.
	 */
	@POST
	@Consumes({  "application/json", "application/xml" })
	@Produces({  "application/json", "application/xml" })
	@Path("/payment/partner")
	public Response createPartnerPayment(PaymentRequest request) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			boolean status = activity.addPaymentPartner(request);
			System.out.println(status);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /payment/partner This will create a new payment object from the
	 * payload.
	 */
	@PUT
	@Consumes({  "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/payment/partner")
	public Response updateCustomerPartner(PaymentRequest request) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			boolean status = activity.updatePaymentPartner(request);
			System.out.println(status);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * GET /payment/partner/{partnerId} This will get the partner payment with
	 * the the customerId
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	@Path("/payment/partner/{partnerId}")
	public Response getPartnerPayment(@PathParam("partnerId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		PaymentActivity activity = new PaymentActivity();
		try {
			PaymentRepresentationInterface temp = activity.getPartnerPayment(id);
			return Response.ok(temp).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}
	}

	/*
	 * DELETE /payment/partner This will remove the partner's payment info from
	 * online
	 */
	@DELETE
	@Produces({ "application/xml", "application/json" })
	@Path("/payment/partner/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") String id) throws UnknownHostException {
		try {
			PaymentActivity activity = new PaymentActivity();
			if (activity.deletePartnerPayment(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

}
