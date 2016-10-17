package com.ws.project.service.service;

import java.net.UnknownHostException;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ws.project.service.representation.ProductRepresentation;
import com.ws.project.service.representation.ProductRequest;
import com.ws.project.service.representation.SearchRepresentation;
import com.ws.project.service.representation.SearchRequest;
import com.ws.project.service.workflow.ProductActivity;

@Path("/productservice/")
public class ProductResource {
	
	@GET
	@Produces({"application/json" , "application/xml"})
	@Path("/product/{productId}")
	public ProductRepresentation getProduct(@PathParam("productId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Product) :" + id);
		ProductActivity cusActivity = new ProductActivity(); 
		return cusActivity.getProduct(id);
	}
	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/product")
	public ProductRepresentation createProduct(ProductRequest productRequest) throws UnknownHostException {
		System.out.println("POST METHOD");
		ProductActivity cusActivity = new ProductActivity();
		return cusActivity.createProduct(productRequest);
	}
	
	@POST
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/search")
	public SearchRepresentation productSearch(SearchRequest productRequest) throws UnknownHostException {
		System.out.println("POST SEARCH");
		ProductActivity cusActivity = new ProductActivity();
		cusActivity.searchProduct(productRequest);
		return cusActivity.searchProduct(productRequest);
	}
	
	@PUT
	@Consumes({"application/json" , "application/xml"})
	@Produces({"application/json" , "application/xml"})
	@Path("/product")
	public ProductRepresentation updateProduct(ProductRepresentation  ProductRequest) throws UnknownHostException {
		System.out.println("PUT METHOD");
		ProductActivity cusActivity = new ProductActivity();
		return cusActivity.updateProduct(ProductRequest);
	}
	
	@DELETE
	@Produces({"application/json" , "application/xml"})
	@Path("/product/{productId}")
	public Response deleteProduct(@PathParam("productId") String id) throws UnknownHostException {
		System.out.println("DELETE METHOD: " + id);
		ProductActivity cusActivity = new ProductActivity();
		if (cusActivity.deleteProduct(id)) {
			return Response.ok().build();
		}
		return Response.ok().build();
	}
	
}
