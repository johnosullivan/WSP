package com.ws.project.service.workflow;

import java.net.UnknownHostException;
import com.ws.project.partner.Partner;
import com.ws.project.dao.PartnerDAO;
import com.ws.project.service.representation.PartnerRepresentation;
import com.ws.project.service.representation.PartnerRequest;

public class PartnerActivity {

	public PartnerRepresentation getPartner(String id) throws UnknownHostException {
		Partner Partner = new Partner(id);
		PartnerRepresentation cusRep = new PartnerRepresentation();
		cusRep.setFirstName(Partner.getFirst());
		cusRep.setLastName(Partner.getLast());
		cusRep.setid(Partner.getID());	
		cusRep.setEmail(Partner.getEmail());
		cusRep.setMiddleName(Partner.getMiddle());
		cusRep.setCompany(Partner.getCompany());
		cusRep.setHomepage(Partner.getHomepage());
		return cusRep;
	}
	
	public boolean deletePartner(String id) throws UnknownHostException {
		PartnerDAO db = PartnerDAO.getInstance();
		return db.deletePartnerById(id);	
	}
	
	public PartnerRepresentation updatePartner(PartnerRepresentation request) throws UnknownHostException {
		Partner Partner = new Partner(request.getid());
		Partner.setFirst(request.getFirstName());
		Partner.setMiddle(request.getMiddleName());
		Partner.setLast(request.getLastName());
		Partner.setEmail(request.getEmail());
		if(Partner.update()) {
			return request;
		} 
		return new PartnerRepresentation();
	}
	
	public PartnerRepresentation createPartner(PartnerRequest request) throws UnknownHostException {
		Partner newcusomter = new Partner();
		newcusomter.setFirst(request.getFirstName());
		newcusomter.setMiddle(request.getMiddleName());
		newcusomter.setLast(request.getLastName());
		newcusomter.setEmail(request.getEmail());
		newcusomter.setCompany(request.getCompany());
		newcusomter.setHomepage(request.getHomepage());
		String newid = newcusomter.create();
		PartnerRepresentation cusRep = new PartnerRepresentation();
		cusRep.setFirstName(newcusomter.getFirst());
		cusRep.setLastName(newcusomter.getLast());
		cusRep.setid(newid);	
		cusRep.setEmail(newcusomter.getEmail());
		cusRep.setMiddleName(newcusomter.getMiddle());
		cusRep.setCompany(newcusomter.getCompany());
		cusRep.setHomepage(newcusomter.getHomepage());
		return cusRep;
	}
	
}
