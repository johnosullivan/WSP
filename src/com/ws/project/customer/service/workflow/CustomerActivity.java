package com.ws.project.customer.service.workflow;

import java.net.UnknownHostException;

import com.ws.project.customer.Customer;
import com.ws.project.customer.CustomerAddress;
import com.ws.project.customer.service.representation.AddressRepresention;
import com.ws.project.customer.service.representation.AddressRequest;
import com.ws.project.customer.service.representation.CustomerRepresentation;
import com.ws.project.customer.service.representation.CustomerRequest;
import com.ws.project.dao.CustomerDAO;

public class CustomerActivity {

	public CustomerRepresentation getCustomer(String id) throws UnknownHostException {
		Customer customer = new Customer(id);
		CustomerRepresentation cusRep = new CustomerRepresentation();
		cusRep.setFirstName(customer.getFirst());
		cusRep.setLastName(customer.getLast());
		cusRep.setid(customer.getID());	
		cusRep.setEmail(customer.getEmail());
		cusRep.setMiddleName(customer.getMiddle());
		return cusRep;
	}
	
	public AddressRepresention addressCustomer(AddressRequest request) throws UnknownHostException {
		CustomerAddress req = new CustomerAddress();
		req.setAddress(request.getAddress());
		req.setCity(request.getCity());
		req.setState(request.getState());
		req.setZip(request.getZip());
		req.setUser(request.getUser());
		String id = req.save();
		AddressRepresention ret = new AddressRepresention();
		ret.setAddress(request.getAddress());
		ret.setCity(request.getCity());
		ret.setState(request.getState());
		ret.setZip(request.getZip());
		ret.setUser(request.getUser());
		ret.setId(id);
		return ret;
	}
	
	public boolean deleteCustomer(String id) throws UnknownHostException {
		CustomerDAO db = CustomerDAO.getInstance();
		return db.deleteCustomerById(id);	
	}
	
	public CustomerRepresentation updateCustomer(CustomerRepresentation request) throws UnknownHostException {
		Customer customer = new Customer(request.getid());
		customer.setFirst(request.getFirstName());
		customer.setMiddle(request.getMiddleName());
		customer.setLast(request.getLastName());
		customer.setEmail(request.getEmail());
		if(customer.update()) {
			return request;
		} 
		return new CustomerRepresentation();
	}
	
	public CustomerRepresentation createCustomer(CustomerRequest request) throws UnknownHostException {
		Customer newcusomter = new Customer();
		newcusomter.setFirst(request.getFirstName());
		newcusomter.setMiddle(request.getMiddleName());
		newcusomter.setLast(request.getLastName());
		newcusomter.setEmail(request.getEmail());
		String newid = newcusomter.create();
		CustomerRepresentation cusRep = new CustomerRepresentation();
		cusRep.setFirstName(newcusomter.getFirst());
		cusRep.setLastName(newcusomter.getLast());
		cusRep.setid(newid);	
		cusRep.setEmail(newcusomter.getEmail());
		cusRep.setMiddleName(newcusomter.getMiddle());
		return cusRep;
	}
	
}
