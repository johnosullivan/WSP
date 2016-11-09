package service.partner.service;

import java.net.UnknownHostException;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import service.partner.representation.PartnerAddressRepresentation;
import service.partner.representation.PartnerAddressRequest;
import service.partner.representation.PartnerPhoneRepresentation;
import service.partner.representation.PartnerPhoneRequest;
import service.partner.representation.PartnerRepresentation;
import service.partner.representation.PartnerRequest;
import service.partner.workflow.PartnerActivity;

@Path("/partnerservice/")
public class PartnerResource {
	/*
	 * GET /partner/{partnerId} Will get the partner object with id
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/{partnerId}")
	public Response getPartner(@PathParam("partnerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerRepresentation status = activity.getPartner(id);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /partner Will create new partner object with id
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner")
	public Response createPartner(PartnerRequest PartnerRequest) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerRepresentation status = activity.createPartner(PartnerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /partner Will update the partner object from the payload
	 */
	@PUT
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner")
	public Response updatePartner(PartnerRepresentation PartnerRequest) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerRepresentation status = activity.updatePartner(PartnerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /partner/{partnerId} Will delete the partner with id
	 */
	@DELETE
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") String id) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			if (activity.deletePartner(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /partner/phone Will create a customer's phone object
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/phone")
	public Response createPartnerPhone(PartnerPhoneRequest customerRequest) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerPhoneRepresentation status = activity.createPartnerPhone(customerRequest);
			System.out.println(status);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /partner/address Will create a customer's address object
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/address")
	public Response createPartnerAddress(PartnerAddressRequest req) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerAddressRepresentation status = activity.createPartnerAddress(req);
			System.out.println(status);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /partner/address/{addressId} Will delete partner's address with id
	 */
	@DELETE
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/address/{addressId}")
	public Response deletePartnerAddress(@PathParam("addressId") String id) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			if (activity.deletePartnerAddress(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /partner/phone/{phoneId} Will delete partner's phone with id
	 */
	@DELETE
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/phone/{phoneId}")
	public Response deletePartnerPhone(@PathParam("phoneId") String id) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			if (activity.deletePartnerPhone(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT partner/address Will update partner's address with payload
	 */
	@PUT
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/address")
	public Response updatePartnerAddress(PartnerAddressRepresentation customerRequest) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerAddressRepresentation status = activity.updatePartnerAddress(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT partner/phone Will update partner's phone with payload
	 */
	@PUT
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@Path("/partner/phone")
	public Response updatePartnerPhone(PartnerPhoneRepresentation customerRequest) throws UnknownHostException {
		try {
			PartnerActivity activity = new PartnerActivity();
			PartnerPhoneRepresentation status = activity.updatePartnerPhone(customerRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

}
