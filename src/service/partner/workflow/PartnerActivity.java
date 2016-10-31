package service.partner.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import dal.address.AddressDAO;
import dal.partner.PartnerDAO;
import dal.phone.PhoneDAO;
import model.customer.CustomerAddress;
import model.customer.CustomerPhone;
import model.partner.Partner;
import model.partner.PartnerAddress;
import model.partner.PartnerPhone;
import service.partner.representation.PartnerAddressRepresentation;
import service.partner.representation.PartnerAddressRequest;
import service.partner.representation.PartnerPhoneRepresentation;
import service.partner.representation.PartnerPhoneRequest;
import service.partner.representation.PartnerRepresentation;
import service.partner.representation.PartnerRequest;

public class PartnerActivity {

	public PartnerRepresentation getPartner(String id) throws UnknownHostException {
		Partner partner = new Partner(id);
		PartnerRepresentation cusRep = new PartnerRepresentation();
		cusRep.setFirstName(partner.getFirst());
		cusRep.setLastName(partner.getLast());
		cusRep.setid(partner.getID());
		cusRep.setEmail(partner.getEmail());
		cusRep.setMiddleName(partner.getMiddle());
		cusRep.setCompany(partner.getCompany());
		cusRep.setHomepage(partner.getHomepage());

		ArrayList<PartnerAddressRepresentation> addressdata = new ArrayList<PartnerAddressRepresentation>();

		for (PartnerAddress temp : partner.getAllAddress()) {
			PartnerAddressRepresentation tempa = new PartnerAddressRepresentation();
			tempa.setAddress(temp.getAddress());
			tempa.setCity(temp.getCity());
			tempa.setState(temp.getState());
			tempa.setId(temp.getID());
			tempa.setUser(temp.getUser());
			tempa.setZip(temp.getZip());
			addressdata.add(tempa);
		}

		ArrayList<PartnerPhoneRepresentation> phonedata = new ArrayList<PartnerPhoneRepresentation>();

		for (PartnerPhone temp : partner.getAllPhone()) {
			PartnerPhoneRepresentation tempa = new PartnerPhoneRepresentation();
			tempa.setPhone(temp.getPhone());
			tempa.setType(temp.getType());
			tempa.setId(temp.getID());
			tempa.setUser(temp.getUser());
			phonedata.add(tempa);
		}

		cusRep.setAddresses(addressdata);
		cusRep.setPhones(phonedata);

		return cusRep;
	}

	public boolean deletePartner(String id) throws UnknownHostException {
		PartnerDAO db = PartnerDAO.getInstance();
		return db.deletePartnerById(id);
	}

	public PartnerAddressRepresentation createPartnerAddress(PartnerAddressRequest request)
			throws UnknownHostException {
		CustomerAddress canew = new CustomerAddress();
		canew.setAddress(request.getAddress());
		canew.setCity(request.getCity());
		canew.setState(request.getState());
		canew.setUser(request.getUser());
		canew.setZip(request.getZip());
		String id = canew.save();
		PartnerAddressRepresentation s = new PartnerAddressRepresentation();
		s.setAddress(request.getAddress());
		s.setCity(request.getCity());
		s.setState(request.getState());
		s.setUser(request.getUser());
		s.setZip(request.getZip());
		System.out.println(id);
		s.setId(id);
		return s;
	}

	public PartnerPhoneRepresentation createPartnerPhone(PartnerPhoneRequest request) throws UnknownHostException {
		CustomerPhone canew = new CustomerPhone();
		canew.setPhone(request.getPhone());
		canew.setType(request.getType());
		canew.setUser(request.getUser());
		String id = canew.save();
		System.out.println(id);
		PartnerPhoneRepresentation s = new PartnerPhoneRepresentation();
		s.setPhone(request.getPhone());
		s.setType(request.getType());
		s.setUser(request.getUser());
		s.setId(id);
		return s;
	}

	public PartnerAddressRepresentation updatePartnerAddress(PartnerAddressRepresentation request)
			throws UnknownHostException {
		PartnerAddress partaddress = new PartnerAddress(request.getId());
		partaddress.setAddress(request.getAddress());
		partaddress.setCity(request.getCity());
		partaddress.setState(request.getState());
		partaddress.setZip(request.getZip());
		partaddress.update();
		PartnerAddressRepresentation s = new PartnerAddressRepresentation();
		s.setAddress(request.getAddress());
		s.setCity(request.getCity());
		s.setState(request.getState());
		s.setZip(request.getZip());
		s.setUser(request.getUser());
		s.setId(request.getId());
		return s;
	}

	public PartnerPhoneRepresentation updatePartnerPhone(PartnerPhoneRepresentation request)
			throws UnknownHostException {
		PartnerPhone partphone = new PartnerPhone(request.getId());
		partphone.setPhone(request.getPhone());
		partphone.setType(request.getType());
		partphone.update();
		PartnerPhoneRepresentation s = new PartnerPhoneRepresentation();
		s.setPhone(request.getPhone());
		s.setType(request.getType());
		s.setUser(request.getUser());
		s.setId(request.getId());
		return s;
	}

	public boolean deletePartnerPhone(String id) throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		return db.deletePhoneById(id);
	}

	public boolean deletePartnerAddress(String id) throws UnknownHostException {
		AddressDAO db = AddressDAO.getInstance();
		return db.deleteAddressById(id);
	}

	public PartnerRepresentation updatePartner(PartnerRepresentation request) throws UnknownHostException {
		// Catches Bad Requests
		if (request.getFirstName().equals("")) {
			throw new UnknownHostException();
		}
		if (request.getLastName().equals("")) {
			throw new UnknownHostException();
		}
		if (request.getEmail().equals("")) {
			throw new UnknownHostException();
		}

		Partner Partner = new Partner(request.getid());
		Partner.setFirst(request.getFirstName());
		Partner.setMiddle(request.getMiddleName());
		Partner.setLast(request.getLastName());
		Partner.setEmail(request.getEmail());
		if (Partner.update()) {
			return request;
		}
		return new PartnerRepresentation();
	}

	public PartnerRepresentation createPartner(PartnerRequest request) throws UnknownHostException {
		// Catches Bad Requests
		if (request.getFirstName().equals("")) {
			throw new UnknownHostException();
		}
		if (request.getLastName().equals("")) {
			throw new UnknownHostException();
		}
		if (request.getEmail().equals("")) {
			throw new UnknownHostException();
		}

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
