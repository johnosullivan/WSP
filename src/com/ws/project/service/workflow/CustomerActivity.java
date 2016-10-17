package com.ws.project.service.workflow;

import java.net.UnknownHostException;

import com.ws.project.customer.Customer;
import com.ws.project.dao.CustomerDAO;
//import com.ws.project.customer.Customer;
import com.ws.project.service.representation.CustomerRepresentation;
import com.ws.project.service.representation.CustomerRequest;

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
