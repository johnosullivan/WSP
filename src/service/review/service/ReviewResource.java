package service.review.service;

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

import service.review.representation.ProductReviewsRepresentation;
import service.review.representation.ReviewRepresentation;
import service.review.representation.ReviewRequest;
import service.review.workflow.ReviewActivity;

@Path("/reviewservice/")
public class ReviewResource {

	/*
	 * POST /review This will create the review from the payload of request.
	 */
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/review")
	public Response createProduct(ReviewRequest req) throws UnknownHostException {
		try {
			System.out.println("POST METHOD");
			ReviewActivity activity = new ReviewActivity();
			ReviewRepresentation status = activity.createReview(req);

			if (!status.getId().equals("")) {
				return Response.ok(status).build();
			} else {
				return Response.status(400).build();
			}
		} catch (NullPointerException e) {
			return Response.status(400).build();
		}
	}

	/*
	 * GET /review/{reviewId} This get the review at an id.
	 */
	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/review/{reviewId}")
	public Response getReview(@PathParam("reviewId") @WebParam(name = "arg0") String id) throws UnknownHostException {
		System.out.println("GET METHOD (Product) :" + id);
		ReviewActivity activity = new ReviewActivity();
		try {
			ReviewRepresentation temp = activity.getReview(id);
			return Response.ok(temp).build();
		} catch (NullPointerException e) {
			return Response.status(400).build();
		}
	}

	/*
	 * GET /reviews/product/{productId} This get the reviews at an id.
	 */
	@GET
	@Produces({ "application/json", "application/xml" })
	@Path("/reviews/product/{productId}")
	public Response getProductReviews(@PathParam("productId") @WebParam(name = "arg0") String id)
			throws UnknownHostException {
		System.out.println("GET METHOD (Review) :" + id);
		ReviewActivity activity = new ReviewActivity();
		try {
			ProductReviewsRepresentation temp = activity.getReviews(id);
			return Response.ok(temp).build();
		} catch (NullPointerException e) {
			return Response.status(400).build();
		}
	}

	/*
	 * DELETE /review This will remove the customer's review
	 */
	@DELETE
	@Produces({ "application/json", "application/xml" })
	@Path("/review/{reviewId}")
	public Response deleteCustomer(@PathParam("reviewId") String id) throws UnknownHostException {
		try {
			ReviewActivity activity = new ReviewActivity();
			if (activity.deleteReview(id)) {
				return Response.ok().build();
			}
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.status(400).build();
		}

	}

	/*
	 * PUT /review This will update the reviewed info in the database.
	 */
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Path("/review")
	public Response updateReview(ReviewRequest req) throws UnknownHostException {
		System.out.println("PUT METHOD");
		try {
			ReviewActivity activity = new ReviewActivity();
			ReviewRepresentation temp = activity.updatedReview(req);
			return Response.ok(temp).build();
		} catch (NullPointerException e) {
			return Response.status(400).build();
		}
	}

}
