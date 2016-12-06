package service.customer.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.core.UriInfo;

import dal.address.AddressDAO;
import dal.customer.CustomerDAO;
import dal.phone.PhoneDAO;
import model.customer.Customer;
import model.customer.CustomerAddress;
import model.customer.CustomerPhone;
import service.Link;
import service.customer.representation.CustomerAddressRepresentation;
import service.customer.representation.CustomerAddressRequest;
import service.customer.representation.CustomerPhoneRepresentation;
import service.customer.representation.CustomerPhoneRequest;
import service.customer.representation.CustomerRepresentation;
import service.customer.representation.CustomerRequest;

public class CustomerActivity {

	public static String customerservice = "customerservice/customer";
	
	
	
	public CustomerAddressRepresentation getAddress(String id, UriInfo uri) throws UnknownHostException {
		CustomerAddress address = new CustomerAddress(id);
		
		CustomerAddressRepresentation temp = new CustomerAddressRepresentation();
		temp.setAddress(address.getAddress());
		temp.setCity(address.getCity());
		temp.setState(address.getState());
		temp.setZip(address.getZip());
		temp.setUser(address.getUser());
		Link linkview = new Link("Update",uri.getBaseUri() + customerservice + "/address/" + address.getID(),"PUT","application/json");
		Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/address/" + address.getID(),"DELETE","application/json");
		temp.setLinks(linkview,linkdelete);
		
		return temp;
	}
	
	public CustomerRepresentation getCustomer(String id,UriInfo uri) throws UnknownHostException {
		Customer customer = new Customer(id);
		CustomerRepresentation cusRep = new CustomerRepresentation();
		cusRep.setFirstName(customer.getFirst());
		cusRep.setLastName(customer.getLast());
		cusRep.setid(customer.getID());
		cusRep.setEmail(customer.getEmail());
		cusRep.setMiddleName(customer.getMiddle());
		cusRep.setPropic(customer.getPP());
		ArrayList<CustomerAddressRepresentation> addressdata = new ArrayList<CustomerAddressRepresentation>();

		for (CustomerAddress temp : customer.getAllAddress()) {
			CustomerAddressRepresentation tempa = new CustomerAddressRepresentation();
			tempa.setAddress(temp.getAddress());
			tempa.setCity(temp.getCity());
			tempa.setState(temp.getState());
			tempa.setId(temp.getID());
			tempa.setUser(temp.getUser());
			tempa.setZip(temp.getZip());
			
			Link linkview = new Link("View",uri.getBaseUri() + customerservice + "/address/" + temp.getID(),"GET","application/json");
			Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/address/" + temp.getID(),"DELETE","application/json");
			tempa.setLinks(linkview,linkdelete);
			
			addressdata.add(tempa);
		}

		ArrayList<CustomerPhoneRepresentation> phonedata = new ArrayList<CustomerPhoneRepresentation>();

		for (CustomerPhone temp : customer.getAllPhone()) {
			CustomerPhoneRepresentation tempa = new CustomerPhoneRepresentation();
			tempa.setPhone(temp.getPhone());
			tempa.setType(temp.getType());
			tempa.setId(temp.getID());
			tempa.setUser(temp.getUser());
			
			Link linkview = new Link("View",uri.getBaseUri() + customerservice + "/phone/" + temp.getID(),"GET","application/json");
			Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/phone/" + temp.getID(),"DELETE","application/json");
			tempa.setLinks(linkview,linkdelete);
			
			phonedata.add(tempa);
		}

		cusRep.setAddresses(addressdata);
		cusRep.setPhones(phonedata);

		return cusRep;
	}

	public CustomerAddressRepresentation addressCustomer(CustomerAddressRequest request, UriInfo uri) throws UnknownHostException {
		CustomerAddress req = new CustomerAddress();
		req.setAddress(request.getAddress());
		req.setCity(request.getCity());
		req.setState(request.getState());
		req.setZip(request.getZip());
		req.setUser(request.getUser());
		String id = req.save();
		CustomerAddressRepresentation ret = new CustomerAddressRepresentation();
		ret.setAddress(request.getAddress());
		ret.setCity(request.getCity());
		ret.setState(request.getState());
		ret.setZip(request.getZip());
		ret.setUser(request.getUser());
		ret.setId(id);
		Link linkview = new Link("View",uri.getBaseUri() + customerservice + "/phone/" + ret.getId(),"GET","application/json");
		Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/phone/" + ret.getId(),"DELETE","application/json");
		ret.setLinks(linkview,linkdelete);
		return ret;
	}

	public CustomerAddressRepresentation updateCustomerAddress(CustomerAddressRepresentation request)
			throws UnknownHostException {
		CustomerAddress cusaddress = new CustomerAddress(request.getId());
		cusaddress.setAddress(request.getAddress());
		cusaddress.setCity(request.getCity());
		cusaddress.setState(request.getState());
		cusaddress.setZip(request.getZip());

		cusaddress.update();
		CustomerAddressRepresentation s = new CustomerAddressRepresentation();
		s.setAddress(request.getAddress());
		s.setCity(request.getCity());
		s.setState(request.getState());
		s.setZip(request.getZip());
		s.setUser(request.getUser());
		s.setId(request.getId());
		return s;
	}

	public CustomerPhoneRepresentation updateCustomerPhone(CustomerPhoneRepresentation request)
			throws UnknownHostException {
		CustomerPhone cusphone = new CustomerPhone(request.getId());
		cusphone.setPhone(request.getPhone());
		cusphone.setType(request.getType());
		cusphone.update();
		CustomerPhoneRepresentation s = new CustomerPhoneRepresentation();
		s.setPhone(request.getPhone());
		s.setType(request.getType());
		s.setUser(request.getUser());
		s.setId(request.getId());
		return s;
	}

	public boolean deleteCustomer(String id) throws UnknownHostException {
		CustomerDAO db = CustomerDAO.getInstance();
		return db.deleteCustomerById(id);
	}

	public boolean deleteCustomerPhone(String id) throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		return db.deletePhoneById(id);
	}

	public boolean deleteCustomerAddress(String id) throws UnknownHostException {
		AddressDAO db = AddressDAO.getInstance();
		return db.deleteAddressById(id);
	}

	public CustomerRepresentation updateCustomer(CustomerRepresentation request) throws UnknownHostException {
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

		Customer customer = new Customer(request.getid());
		customer.setFirst(request.getFirstName());
		customer.setMiddle(request.getMiddleName());
		customer.setLast(request.getLastName());
		customer.setEmail(request.getEmail());
		if (customer.update()) {
			return request;
		}
		return new CustomerRepresentation();
	}

	public CustomerRepresentation createCustomer(CustomerRequest request) throws UnknownHostException {
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

		Customer newcusomter = new Customer();
		newcusomter.setFirst(request.getFirstName());
		newcusomter.setMiddle(request.getMiddleName());
		newcusomter.setLast(request.getLastName());
		newcusomter.setEmail(request.getEmail());
		newcusomter.setPP(request.getPropic());
		String newid = newcusomter.create();
		CustomerRepresentation cusRep = new CustomerRepresentation();
		cusRep.setFirstName(newcusomter.getFirst());
		cusRep.setLastName(newcusomter.getLast());
		cusRep.setid(newid);
		cusRep.setPropic(newcusomter.getPP());
		cusRep.setEmail(newcusomter.getEmail());
		cusRep.setMiddleName(newcusomter.getMiddle());
		return cusRep;
	}

	public CustomerAddressRepresentation createCustomerAddress(CustomerAddressRequest request,UriInfo uri)
			throws UnknownHostException {
		CustomerAddress canew = new CustomerAddress();
		canew.setAddress(request.getAddress());
		canew.setCity(request.getCity());
		canew.setState(request.getState());
		canew.setUser(request.getUser());
		canew.setZip(request.getZip());
		String id = canew.save();
		System.out.println(id);
		CustomerAddressRepresentation rep = new CustomerAddressRepresentation();
		rep.setAddress(request.getAddress());
		rep.setCity(request.getCity());
		rep.setState(request.getState());
		rep.setUser(request.getUser());
		rep.setZip(request.getZip());
		rep.setId(id);
		Link linkview = new Link("View",uri.getBaseUri() + customerservice + "/phone/" + rep.getId(),"GET","application/json");
		Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/phone/" + rep.getId(),"DELETE","application/json");
		rep.setLinks(linkview,linkdelete);
		return rep;
	}

	public CustomerPhoneRepresentation createCustomerPhone(CustomerPhoneRequest request, UriInfo uri) throws UnknownHostException {
		CustomerPhone canew = new CustomerPhone();
		canew.setPhone(request.getPhone());
		canew.setType(request.getType());
		canew.setUser(request.getUser());
		String id = canew.save();
		System.out.println(id);
		CustomerPhoneRepresentation req = new CustomerPhoneRepresentation();
		req.setPhone(request.getPhone());
		req.setType(request.getType());
		req.setUser(request.getUser());
		req.setId(id);
		Link linkview = new Link("View",uri.getBaseUri() + customerservice + "/phone/" + req.getId(),"GET","application/json");
		Link linkdelete = new Link("Delete",uri.getBaseUri() + customerservice + "/phone/" + req.getId(),"DELETE","application/json");
		req.setLinks(linkview,linkdelete);
		return req;
	}

}
