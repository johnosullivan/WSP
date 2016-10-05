package com.ws.project;

import java.net.UnknownHostException;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

//import com.ws.project.customer.Customer;
import com.ws.project.service.service.CustomerResource;

public class App {
	
	 public static void main(final String[] args) throws UnknownHostException {
		  JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
          sf.setResourceClasses(CustomerResource.class);
          sf.setResourceProvider(CustomerResource.class, new SingletonResourceProvider(new CustomerResource()));
          sf.setAddress("http://localhost:3000/");
          Server server = sf.create();
          System.out.println(server.toString());
	 }
	 
}