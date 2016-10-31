package client.jacksonClient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import model.customer.CustomerAddress;
import service.customer.representation.CustomerAddressRepresentation;
import service.customer.representation.CustomerRepresentation;
import service.customer.representation.CustomerRequest;
import service.order.representation.OrderItemRepresentation;
import service.order.representation.OrderRepresentation;
import service.order.representation.OrderRequest;
import service.partner.representation.PartnerRepresentation;
import service.partner.representation.PartnerRequest;
import service.product.representation.ProductRequest;

public class ClientOrderReview {

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
		WebClient postClientcus = WebClient.create(address, providers);
		WebClient.getConfig(postClientcus).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClientcus).getInInterceptors().add(new LoggingInInterceptor());
		postClientcus = postClientcus.accept("application/json").type("application/json")
				.path("/customerservice/customer");
		String postRequestURI1cus = postClientcus.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURI1cus);
		String postRequestHeaders1cus = postClientcus.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders1cus);
		CustomerRequest customerRequest1 = new CustomerRequest();
		customerRequest1.setFirstName("John");
		customerRequest1.setMiddleName("Nikolas");
		customerRequest1.setLastName("O'Sullivan");
		customerRequest1.setEmail("jnosullivan@mac.com");
		customerRequest1.setPropic("http://www.google.com/somepic");
		String responsePost1cus = postClientcus.post(customerRequest1, String.class);
		System.out.println("POST METHOD Response: " + responsePost1cus);
		End();

		// Get Customer
		Start("Get Customer");
		ObjectMapper objectMappercus = new ObjectMapper();
		CustomerRepresentation cuscus = objectMappercus.readValue(responsePost1cus, CustomerRepresentation.class);
		String customerid = cuscus.getid();
		System.out.println("Customer's id: " + customerid);

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

		ObjectMapper objectMapperaddress = new ObjectMapper();
		CustomerAddressRepresentation addressobject = objectMapperaddress.readValue(responsePost1cusad,
				CustomerAddressRepresentation.class);
		String addressid = addressobject.getId();
		System.out.println("Address id: " + addressid);

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

		Start("Post Create Order");
		WebClient postClientorder = WebClient.create(address, providers);
		WebClient.getConfig(postClientorder).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(postClientorder).getInInterceptors().add(new LoggingInInterceptor());
		postClientorder = postClientorder.accept("application/json").type("application/json")
				.path("/orderservice/order");
		String postRequestURIorder = postClientorder.getCurrentURI().toString();
		System.out.println("Client POST METHOD Request URI:  " + postRequestURIorder);
		String postRequestHeadersorder = postClientorder.getHeaders().toString();
		System.out.println("Client POST METHOD Request Headers:  " + postRequestHeadersorder);

		OrderRequest orderreq = new OrderRequest();
		ArrayList<OrderItemRepresentation> items = new ArrayList<OrderItemRepresentation>();
		orderreq.setAddress(addressid);
		orderreq.setCustomers(customerid);
		OrderItemRepresentation orditem = new OrderItemRepresentation();
		orditem.setProductid(productobj);
		orditem.setQ(5);
		items.add(orditem);
		orderreq.setItems(items);

		String responsePostorder = postClientorder.post(orderreq, String.class);
		System.out.println("POST Order METHOD Response: " + responsePostorder);
		End();

		ObjectMapper objectMapper3 = new ObjectMapper();
		OrderRepresentation order = objectMapper3.readValue(responsePostorder, OrderRepresentation.class);
		String orderid = order.getId();
		System.out.println("Order's id: " + orderid);

		// Get Customer
		Start("Get MyOrders Customer");
		WebClient getClientmyorders = WebClient.create(address, providers);
		WebClient.getConfig(getClientmyorders).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClientmyorders).getInInterceptors().add(new LoggingInInterceptor());
		getClientmyorders = getClientmyorders.accept("application/json").type("application/json")
				.path("/orderservice/orders/customer/" + customerid);
		String getAllRequestURImo = getClientmyorders.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getAllRequestURImo);
		String getAllRequestHeadersmo = getClientmyorders.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeadersmo);
		String getAllResponsemo = getClientmyorders.get(String.class);
		System.out.println("GET METHOD Response: " + getAllResponsemo);
		End();

		// Get Customer
		Start("Get Partner's Orders");
		WebClient getClientporders = WebClient.create(address, providers);
		WebClient.getConfig(getClientporders).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(getClientporders).getInInterceptors().add(new LoggingInInterceptor());
		getClientporders = getClientporders.accept("application/json").type("application/json")
				.path("/orderservice/orders/partner/" + partnerid);
		String getAllRequestURIpo = getClientporders.getCurrentURI().toString();
		System.out.println("Client GET METHOD Request URI:  " + getAllRequestURIpo);
		String getAllRequestHeaderspo = getClientporders.getHeaders().toString();
		System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeaderspo);
		String getAllResponsepo = getClientporders.get(String.class);
		System.out.println("GET METHOD Response: " + getAllResponsepo);
		End();

		// DELETE Customer
		Start("DELETE Customer");
		WebClient deleteClient1cus = WebClient.create(address, providers);
		WebClient.getConfig(deleteClient1cus).getOutInterceptors().add(new LoggingOutInterceptor());
		WebClient.getConfig(deleteClient1cus).getInInterceptors().add(new LoggingInInterceptor());
		deleteClient1cus = deleteClient1cus.accept("application/json").type("application/json")
				.path("/customerservice/customer/" + customerid);
		String deleteRequestURI1cus = deleteClient1cus.getCurrentURI().toString();
		System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI1cus);
		String deleteRequestHeaders2cus = deleteClient1cus.getHeaders().toString();
		System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders2cus);
		deleteClient1cus.delete();
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
