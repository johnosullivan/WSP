package service.product.service;

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

import service.product.representation.ProductRepresentation;
import service.product.representation.ProductRequest;
import service.product.representation.SearchRepresentation;
import service.product.representation.SearchRequest;
import service.product.workflow.ProductActivity;

@Path("/productservice/")
public class ProductResource {
	/*
	 * GET /product/{productId} This get the product at an id.
	 */
	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/product/{productId}")
	public Response getProduct(@PathParam("productId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		try {
			System.out.println("GET METHOD (Product) :" + id);
			ProductActivity activity = new ProductActivity();
			ProductRepresentation status = activity.getProduct(id);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /product This will create the product from the payload of request.
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/product")
	public Response createProduct(ProductRequest productRequest) throws UnknownHostException {
		try {
			System.out.println("POST METHOD");
			ProductActivity activity = new ProductActivity();
			ProductRepresentation status = activity.createProduct(productRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * POST /search This will take the payload info and search the database.
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/search")
	public Response productSearch(SearchRequest productRequest) throws UnknownHostException {
		try {
			System.out.println("POST SEARCH");
			ProductActivity activity = new ProductActivity();
			activity.searchProduct(productRequest);
			SearchRepresentation status = activity.searchProduct(productRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * PUT /product This will update the products info in the database.
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/product")
	public Response updateProduct(ProductRepresentation ProductRequest) throws UnknownHostException {
		try {
			System.out.println("PUT METHOD");
			ProductActivity activity = new ProductActivity();
			ProductRepresentation status = activity.updateProduct(ProductRequest);
			return Response.ok(status).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /product/{productId} This will delete the product with a certain
	 * id.
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/product/{productId}")
	public Response deleteProduct(@PathParam("productId") String id) throws UnknownHostException {
		try {
			System.out.println("DELETE METHOD: " + id);
			ProductActivity activity = new ProductActivity();
			if (activity.deleteProduct(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

}
