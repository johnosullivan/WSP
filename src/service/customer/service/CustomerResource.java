package service.customer.service;

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

import service.customer.representation.CustomerAddressRepresentation;
import service.customer.representation.CustomerAddressRequest;
import service.customer.representation.CustomerPhoneRepresentation;
import service.customer.representation.CustomerPhoneRequest;
import service.customer.representation.CustomerRepresentation;
import service.customer.representation.CustomerRequest;
import service.customer.workflow.CustomerActivity;

@Path("/customerservice/")
public class CustomerResource {
	/*
	 * GET /customer/{customerId} This will get the customer with the the
	 * customerId
	 */
	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/{customerId}")
	public Response getCustomer(@PathParam("customerId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		CustomerActivity activity = new CustomerActivity();
		try {
			CustomerRepresentation temp = activity.getCustomer(id);
			return Response.ok(temp).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /customer This will create a new customer object from the payload.
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer")
	public Response createCustomer(CustomerRequest customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerRepresentation status = activity.createCustomer(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /customer/address This will create a new address object for the
	 * customer
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/address")
	public Response createCustomerAddress(CustomerAddressRequest customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerAddressRepresentation status = activity.createCustomerAddress(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /customer/phone This will create a new phone object for the customer
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/phone")
	public Response createCustomerPhone(CustomerPhoneRequest customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerPhoneRepresentation status = activity.createCustomerPhone(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /customer/address Will update the address object with payload
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/address")
	public Response updateCustomerAddress(CustomerAddressRepresentation customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerAddressRepresentation status = activity.updateCustomerAddress(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /customer/phone Will update the phone object with payload
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/phone")
	public Response updateCustomerPhone(CustomerPhoneRepresentation customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerPhoneRepresentation status = activity.updateCustomerPhone(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /customer Will update the customer object with payload
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/customer")
	public Response updateCustomer(CustomerRepresentation customerRequest) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			CustomerRepresentation status = activity.updateCustomer(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /customer/{customerId} Will delete the customer object with id
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") String id) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			if (activity.deleteCustomer(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /customer/phone/{phoneId} Will delete the customer's phone object
	 * with id
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/phone/{phoneId}")
	public Response deleteCustomerPhone(@PathParam("phoneId") String id) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			if (activity.deleteCustomerPhone(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /customer/phone/{addressId} Will delete the customer's address
	 * object with id
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/customer/address/{addressId}")
	public Response deleteCustomerAddress(@PathParam("addressId") String id) throws UnknownHostException {
		try {
			CustomerActivity activity = new CustomerActivity();
			if (activity.deleteCustomerAddress(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

}
