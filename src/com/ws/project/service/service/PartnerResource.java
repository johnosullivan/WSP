package com.ws.project.service.service;

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

import com.ws.project.service.workflow.PartnerActivity;
import javax.ws.rs.PathParam;
import com.ws.project.service.representation.PartnerRepresentation;
import com.ws.project.service.representation.PartnerRequest;

@Path("/partnerservice/")
public class PartnerResource {
	
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (PARTNER) :" + id);
		PartnerActivity cusActivity = new PartnerActivity(); 
		return cusActivity.getPartner(id);
	}
	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/partner")
	public PartnerRepresentation createPartner(PartnerRequest  PartnerRequest) throws UnknownHostException {
		System.out.println("POST METHOD");
		PartnerActivity cusActivity = new PartnerActivity();
		return cusActivity.createPartner(PartnerRequest);
	}
	
	@PUT
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/partner")
	public PartnerRepresentation updatePartner(PartnerRepresentation  PartnerRequest) throws UnknownHostException {
		System.out.println("PUT METHOD");
		PartnerActivity cusActivity = new PartnerActivity();
		return cusActivity.updatePartner(PartnerRequest);
	}
	
	@DELETE
	@Produces({"application/json" , "application/xml"})
	@Path("/partner/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") String id) throws UnknownHostException {
		System.out.println("DELETE METHOD: " + id);
		PartnerActivity cusActivity = new PartnerActivity();
		if (cusActivity.deletePartner(id)) {
			return Response.ok().build();
		}
		return Response.ok().build();
	}
	
}
