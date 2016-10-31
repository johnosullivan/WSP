package client.jacksonClient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import model.partner.PartnerAddress;
import service.customer.representation.CustomerAddressRepresentation;
import service.partner.representation.PartnerRepresentation;
import service.partner.representation.PartnerRequest;
import service.payment.representation.PaymentRequest;

public class ClientPartnerPayment {

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

		// Post Partner
		Start("Post Partner");
		WebClient postClient1 = WebClient.create(address, providers);
		WebClient.getConfig(postClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClient1).getInInterceptors().add(new LoggingInInterceptor());
		postClient1 = postClient1.accept("application/json").type("application/json").path("/partnerservice/partner");
		String postRequestURI1 = postClient1.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI1);
		String postRequestHeaders1 = postClient1.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders1);
		PartnerRequest partnerRequest1 = new PartnerRequest();
		partnerRequest1.setFirstName("John");
		partnerRequest1.setMiddleName("Nikolas");
		partnerRequest1.setLastName("O'Sullivan");
		partnerRequest1.setEmail("jnosullivan@mac.com");
		partnerRequest1.setHomepage("http://otechsoftware.com");
		partnerRequest1.setCompany("O'Tech Software");
		String responsePost1 = postClient1.post(partnerRequest1, String.class);
		System.out.println("POST METHOD Response: " + responsePost1);
		End();

		// Get Partner
		Start("Get Partner");
		ObjectMapper objectMapper = new ObjectMapper();
		PartnerRepresentation cus = objectMapper.readValue(responsePost1, PartnerRepresentation.class);
		String partnerid = cus.getid();
		System.out.println("Partner's id: " + partnerid);
		WebClient getClient1 = WebClient.create(address, providers);
		WebClient.getConfig(getClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClient1).getInInterceptors().add(new LoggingInInterceptor());
		getClient1 = getClient1.accept("application/json").type("application/json")
				.path("/partnerservice/partner/" + partnerid);
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
				.path("/partnerservice/partner/address");
		String postRequestURI1cusad = postClientad.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI1cusad);
		String postRequestHeaders1cusad = postClientad.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders1cusad);
		PartnerAddress customerRequestaddress = new PartnerAddress();
		customerRequestaddress.setAddress("723 Ouilmette Ln");
		customerRequestaddress.setState("IL");
		customerRequestaddress.setCity("Wilmette");
		customerRequestaddress.setZip(60091);
		customerRequestaddress.setUser(partnerid);
		String responsePost1cusad = postClientad.post(customerRequestaddress, String.class);
		System.out.println("POST Address Response: " + responsePost1cusad);
		End();
		
		ObjectMapper objectMapperaddress = new ObjectMapper();
		CustomerAddressRepresentation addressobject = objectMapperaddress.readValue(responsePost1cusad,
				CustomerAddressRepresentation.class);
		String addressid = addressobject.getId();
		System.out.println("Address id: " + addressid);

		Start("POST Add Payment");
		WebClient postClientpay = WebClient.create(address, providers);
		WebClient.getConfig(postClientpay).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClientpay).getInInterceptors().add(new LoggingInInterceptor());
		postClientpay = postClientpay.accept("application/json").type("application/json")
				.path("/paymentservice/payment/partner");
		String postRequestURIpay = postClientpay.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURIpay);
		String postRequestHeaderspay = postClientpay.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaderspay);
		PaymentRequest payreq = new PaymentRequest();
		payreq.setBilling(addressid);
		payreq.setType("CC");
		payreq.setccnum("1234432112344321");
		payreq.setccexp("01/12");
		payreq.setccsec("123");
		payreq.setUser(partnerid);
		String payreqs = postClientpay.post(payreq, String.class);
		System.out.println("POST Payment Response: " + payreqs);
		End();

		// Get Customer
		Start("Get Partner's Payment");
		WebClient getClientgpay = WebClient.create(address, providers);
		WebClient.getConfig(getClientgpay).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClientgpay).getInInterceptors().add(new LoggingInInterceptor());
		getClientgpay = getClientgpay.accept("application/json").type("application/json")
				.path("/paymentservice/payment/partner/" + partnerid);
		String getAllRequestURIgpay = getClientgpay.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getAllRequestURIgpay);
		String getAllRequestHeadersgpay = getClientgpay.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeadersgpay);
		String getAllResponsegpay = getClientgpay.get(String.class);
		System.out.println("GET METHOD Response: " + getAllResponsegpay);
		End();
		
		// PUT Partner
		Start("PUT Partner");
		WebClient putClient3 = WebClient.create(address, providers);
		WebClient.getConfig(putClient3).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(putClient3).getInInterceptors().add(new LoggingInInterceptor());
		putClient3 = putClient3.accept("application/json").type("application/json").path("/partnerservice/partner");
		String putRequestURI2 = putClient3.getCurrentURI().toString();
		System.out.println("Client PUT METHOD Request URI:  " + putRequestURI2);
		String putRequestHeaders2 = putClient3.getHeaders().toString();
		System.out.println("Client PUT METHOD Request Headers:  " + putRequestHeaders2);
		PartnerRepresentation partnerPut = new PartnerRepresentation();
		partnerPut.setFirstName("John");
		partnerPut.setMiddleName("Nikolas");
		partnerPut.setLastName("O'Sullivan");
		partnerPut.setEmail("josullivan1@luc.edu");
		partnerPut.setHomepage("http://otechsoftware.com");
		partnerPut.setCompany("O'Tech Software");
		partnerPut.setid(partnerid);
		String responsePut = putClient3.put(partnerPut, String.class);
		System.out.println("PUT METHOD Response: " + responsePut);
		End();

		// DELETE Partner
		Start("DELETE Partner");
		WebClient deleteClient1 = WebClient.create(address, providers);
		WebClient.getConfig(deleteClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(deleteClient1).getInInterceptors().add(new LoggingInInterceptor());
		deleteClient1 = deleteClient1.accept("application/json").type("application/json")
				.path("/partnerservice/partner/" + partnerid);
		String deleteRequestURI1 = deleteClient1.getCurrentURI().toString();
		System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI1);
		String deleteRequestHeaders2 = putClient3.getHeaders().toString();
		System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders2);
		deleteClient1.delete();
		End();

	}

}
