package com.ws.project.service.workflow;

import java.net.UnknownHostException;

//import com.ws.project.customer.Customer;
import com.ws.project.service.representation.CustomerRepresentation;

public class CustomerActivity {

	public CustomerRepresentation getCustomer(String id) throws UnknownHostException {
		//Customer customer = new Customer(id);
		CustomerRepresentation cusRep = new CustomerRepresentation();
		cusRep.setFirstName("John");
		cusRep.setLastName("O'Sullivan");
		cusRep.setid("1234567890");	
		cusRep.setEmail("jnosullivan@mac.com");
		cusRep.setMiddleName("Middle");
		return cusRep;
	}
	
}
