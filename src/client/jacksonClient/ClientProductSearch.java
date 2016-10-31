package client.jacksonClient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import service.partner.representation.PartnerRepresentation;
import service.partner.representation.PartnerRequest;
import service.product.representation.ProductRequest;
import service.product.representation.SearchRequest;

public class ClientProductSearch {

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

		ObjectMapper objectMapper = new ObjectMapper();
		PartnerRepresentation cus = objectMapper.readValue(responsePost1, PartnerRepresentation.class);
		String partnerid = cus.getid();
		System.out.println("Partner's id: " + partnerid);

		// Post Product
		Start("Post Product");
		WebClient postClient2 = WebClient.create(address, providers);
		WebClient.getConfig(postClient2).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClient2).getInInterceptors().add(new LoggingInInterceptor());
		postClient2 = postClient2.accept("application/json").type("application/json").path("/productservice/product");
		String postRequestURI2 = postClient1.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI2);
		String postRequestHeaders2 = postClient1.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders2);
		ProductRequest productrequest = new ProductRequest();
		productrequest.setName("iPhone 7 Plus");
		productrequest.setDescription("This is the greatest phone ever.");
		productrequest.setCost(650);
		productrequest.setCurcode("US");
		productrequest.setInvein(200);
		productrequest.setPartnerid(partnerid);
		String responsePost2 = postClient2.post(productrequest, String.class);
		System.out.println("POST Product METHOD Response: " + responsePost2);
		End();

		ObjectMapper objectMapper2 = new ObjectMapper();
		ProductRequest product = objectMapper2.readValue(responsePost2, ProductRequest.class);
		String productobj = product.getID();
		System.out.println("Product's id: " + productobj);

		// Post Product
		Start("Put Product");
		WebClient putClientpro = WebClient.create(address, providers);
		WebClient.getConfig(putClientpro).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(putClientpro).getInInterceptors().add(new LoggingInInterceptor());
		putClientpro = putClientpro.accept("application/json").type("application/json").path("/productservice/product");
		String postRequestURIputpro = putClientpro.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURIputpro);
		String postRequestHeadersproput = postClient1.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeadersproput);
		ProductRequest productrequestput = new ProductRequest();
		productrequestput.setName("iPhone 6 Plus");
		productrequestput.setDescription("This is the greatest phone ever.");
		productrequestput.setCost(750);
		productrequestput.setCurcode("US");
		productrequestput.setInvein(200);
		productrequestput.setPartnerid(partnerid);
		productrequestput.setID(productobj);
		String responsePostproput = putClientpro.put(productrequestput, String.class);
		System.out.println("POST Product METHOD Response: " + responsePostproput);
		End();

		// Get Partner
		Start("Get Product");
		WebClient getClientpro = WebClient.create(address, providers);
		WebClient.getConfig(getClientpro).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClientpro).getInInterceptors().add(new LoggingInInterceptor());
		getClientpro = getClientpro.accept("application/json").type("application/json")
				.path("/productservice/product/" + productobj);
		String getAllRequestURIpro = getClientpro.getCurrentURI().toString();
		System.out.println("Client GET Product Request URI:  " + getAllRequestURIpro);
		String getAllRequestHeaderspro = getClientpro.getHeaders().toString();
		System.out.println("Client GET Product Request Headers:  " + getAllRequestHeaderspro);
		String getAllResponsepro = getClientpro.get(String.class);
		System.out.println("GET Product Response: " + getAllResponsepro);
		End();

		// Post Product
		Start("Post Search Product");
		WebClient postClientsearch = WebClient.create(address, providers);
		WebClient.getConfig(postClientsearch).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClientsearch).getInInterceptors().add(new LoggingInInterceptor());
		postClientsearch = postClientsearch.accept("application/json").type("application/json")
				.path("/productservice/search");
		String postRequestURIsearch = postClientsearch.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURIsearch);
		String postRequestHeaderssearch = postClientsearch.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaderssearch);
		SearchRequest searchrequest = new SearchRequest();
		searchrequest.setSearchterm("iPhone");
		String responsePostsearch = postClientsearch.post(searchrequest, String.class);
		System.out.println("POST Product METHOD Response: " + responsePostsearch);
		End();

		// Delete Partner
		Start("DELETE Product");
		WebClient deleteClientpro = WebClient.create(address, providers);
		WebClient.getConfig(deleteClientpro).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(deleteClientpro).getInInterceptors().add(new LoggingInInterceptor());
		deleteClientpro = deleteClientpro.accept("application/json").type("application/json")
				.path("/productservice/product/" + productobj);
		String deleteRequestURIprod = deleteClientpro.getCurrentURI().toString();
		System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURIprod);
		String deleteRequestHeadersprod = deleteClientpro.getHeaders().toString();
		System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeadersprod);
		deleteClientpro.delete();
		End();

		// Delete Partner
		Start("DELETE Partner");
		WebClient deleteClient1 = WebClient.create(address, providers);
		WebClient.getConfig(deleteClient1).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(deleteClient1).getInInterceptors().add(new LoggingInInterceptor());
		deleteClient1 = deleteClient1.accept("application/json").type("application/json")
				.path("/partnerservice/partner/" + partnerid);
		String deleteRequestURI1 = deleteClient1.getCurrentURI().toString();
		System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI1);
		String deleteRequestHeaders2 = deleteClient1.getHeaders().toString();
		System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders2);
		deleteClient1.delete();
		End();

	}

}
