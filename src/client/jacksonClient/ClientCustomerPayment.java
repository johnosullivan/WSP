package client.jacksonClient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import model.customer.CustomerAddress;
import service.customer.representation.CustomerRepresentation;
import service.customer.representation.CustomerRequest;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class ClientCustomerPayment {

	static String address = ClientConfig.address;

	public static void Start(String x) {
		System.out.println(
				"[============================================================================================================]");
		System.out.println("CLIENT: " + x);
	}

	public static void End() {
		System.out.println(
				"[============================================================================================================]");
	}

	public static void main(String args[]) throws Exception {
		List<Object> providers = new ArrayList<Object>();
		JacksonJsonProvider provider = new JacksonJsonProvider();
		provider.addUntouchable(Response.class);
		providers.add(provider);

		// POST Customer
		Start("POST Customer");
		WebClient postClient1 = WebClient.create(address, providers);
		WebClient.getConfig(postClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClient1).getInInterceptors().add(new LoggingInInterceptor());
		postClient1 = postClient1.accept("application/json").type("application/json").path("/customerservice/customer");
		String postRequestURI1 = postClient1.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI1);
		String postRequestHeaders1 = postClient1.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders1);
		CustomerRequest customerRequest1 = new CustomerRequest();
		customerRequest1.setFirstName("John");
		customerRequest1.setMiddleName("Nikolas");
		customerRequest1.setLastName("O'Sullivan");
		customerRequest1.setEmail("jnosullivan@mac.com");
		customerRequest1.setPropic("http://www.google.com/somepic");
		String responsePost1 = postClient1.post(customerRequest1, String.class);
		System.out.println("POST METHOD Response: " + responsePost1);
		End();

		// Get Customer
		Start("Get Customer");
		ObjectMapper objectMapper = new ObjectMapper();
		CustomerRepresentation cus = objectMapper.readValue(responsePost1, CustomerRepresentation.class);
		String customerid = cus.getid();
		System.out.println("Customer's id: " + customerid);

		WebClient getClient1 = WebClient.create(address, providers);
		WebClient.getConfig(getClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClient1).getInInterceptors().add(new LoggingInInterceptor());
		getClient1 = getClient1.accept("application/json").type("application/json")
				.path("/customerservice/customer/" + customerid);
		String getAllRequestURI = getClient1.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getAllRequestURI);
		String getAllRequestHeaders = getClient1.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeaders);
		String getAllResponse = getClient1.get(String.class);
		System.out.println("GET METHOD Response: " + getAllResponse);
		End();

		// Add Address
		Start("POST Add Address");
		WebClient postClientad = WebClient.create(address, providers);
		WebClient.getConfig(postClientad).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClientad).getInInterceptors().add(new LoggingInInterceptor());
		postClientad = postClientad.accept("application/json").type("application/json")
				.path("/customerservice/customer/address");
		String postRequestURI1cusad = postClientad.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI1cusad);
		String postRequestHeaders1cusad = postClientad.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders1cusad);
		CustomerAddress customerRequestaddress = new CustomerAddress();
		customerRequestaddress.setAddress("723 Ouilmette Ln");
		customerRequestaddress.setState("IL");
		customerRequestaddress.setCity("Wilmette");
		customerRequestaddress.setZip(60091);
		customerRequestaddress.setUser(customerid);
		String responsePost1cusad = postClientad.post(customerRequestaddress, String.class);
		System.out.println("POST Address Response: " + responsePost1cusad);
		End();

		// PUT Customer
		Start("PUT Customer");
		WebClient putClient3 = WebClient.create(address, providers);
		WebClient.getConfig(putClient3).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(putClient3).getInInterceptors().add(new LoggingInInterceptor());
		putClient3 = putClient3.accept("application/json").type("application/json").path("/customerservice/customer");
		String putRequestURI2 = putClient3.getCurrentURI().toString();
		System.out.println("Client PUT METHOD Request URI:  " + putRequestURI2);
		String putRequestHeaders2 = putClient3.getHeaders().toString();
		System.out.println("Client PUT METHOD Request Headers:  " + putRequestHeaders2);
		CustomerRepresentation customerPut = new CustomerRepresentation();
		customerPut.setFirstName("John");
		customerPut.setMiddleName("Nikolas");
		customerPut.setLastName("O'Sullivan");
		customerPut.setEmail("josullivan1@luc.edu");
		customerPut.setPropic("http://www.google.com/somepic");
		customerPut.setid(customerid);
		String responsePut = putClient3.put(customerPut, String.class);
		System.out.println("PUT METHOD Response: " + responsePut);
		End();

		// DELETE Customer
		Start("DELETE Customer");
		WebClient deleteClient1 = WebClient.create(address, providers);
		WebClient.getConfig(deleteClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(deleteClient1).getInInterceptors().add(new LoggingInInterceptor());
		deleteClient1 = deleteClient1.accept("application/json").type("application/json")
				.path("/customerservice/customer/" + customerid);
		String deleteRequestURI1 = deleteClient1.getCurrentURI().toString();
		System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI1);
		String deleteRequestHeaders2 = putClient3.getHeaders().toString();
		System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders2);
		deleteClient1.delete();
		End();

	}

}
