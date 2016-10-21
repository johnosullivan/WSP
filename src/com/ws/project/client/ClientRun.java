package com.ws.project.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.ws.project.customer.service.representation.CustomerRequest;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class ClientRun {

	static String address = "http://localhost:8081";
	
	public static void Start(String x) { System.out.println("[============================================================================================================]"); System.out.println("METHOD: " + x); }
	public static void End() { System.out.println("[============================================================================================================]"); }
	
	public static void main(String args[]) throws Exception {
		List<Object> providers = new ArrayList<Object>();
		JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.addUntouchable(Response.class);
        providers.add(provider);
        WebClient getClient = WebClient.create(address, providers);
        WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
        // GET METHOD TEST
        Start("GET");
        getClient = getClient.accept("application/json").type("application/json").path("/customerservice/customer/1111");
        String getRequestURI = getClient.getCurrentURI().toString();
        System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
        String getRequestHeaders = getClient.getHeaders().toString();
        System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
        String response = getClient.get(String.class);
        System.out.println("GET METHOD Response: " + response);
		End();
		// POST METHOD TEST
        Start("POST");
		WebClient postClient = WebClient.create(address, providers);
        WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
        WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());
        postClient = postClient.accept("application/xml").type("application/json").path("/customerservice/customer");
        String postRequestURI = postClient.getCurrentURI().toString();
        System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
        String postRequestHeaders = postClient.getHeaders().toString();
        System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("Maria");
        customerRequest.setMiddleName("Nikolas");
        customerRequest.setLastName("O'Sullivan");
        customerRequest.setEmail("meo@mac.com");
     	String responsePost =  postClient.post(customerRequest, String.class);
        System.out.println("POST METHOD Response: " + responsePost);
        End();
	}
	
}
