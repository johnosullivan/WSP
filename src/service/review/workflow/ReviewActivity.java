package service.review.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import dal.review.ReviewDAO;
import model.review.Review;
import model.review.Reviews;
import service.review.representation.ProductReviewRepresentation;
import service.review.representation.ProductReviewsRepresentation;
import service.review.representation.ReviewRepresentation;
import service.review.representation.ReviewRequest;


public class ReviewActivity {

	public ReviewRepresentation createReview(ReviewRequest req) throws UnknownHostException {
		
		System.out.println(req.getProduct());
		System.out.println(req.getReview());
		System.out.println(req.getStars());
		System.out.println(req.getOrder());
		
		Review review = new Review();
		review.setOrder(req.getOrder());
		review.setStars(req.getStars());
		review.setProduct(req.getProduct());
		review.setReview(req.getReview());
		review.setName(req.getName());
		
		String reviewid = review.submit();
		
		
		ReviewRepresentation rep = new ReviewRepresentation();
		rep.setOrder(req.getOrder());
		rep.setStars(req.getStars());
		rep.setProduct(req.getProduct());
		rep.setReview(req.getReview());
		rep.setName(req.getName());
		rep.setId(reviewid);
		
		
		return rep;
	}
	
	public ProductReviewsRepresentation getReviews(String id) throws UnknownHostException {
		Reviews reviewobject = new Reviews();
		ProductReviewsRepresentation respond = new ProductReviewsRepresentation();
		ArrayList<ProductReviewRepresentation> results = new ArrayList<ProductReviewRepresentation>();
		for (Review rev : reviewobject.getReviewsWithId(id)) {
			ProductReviewRepresentation temp = new ProductReviewRepresentation();
			temp.setReview(rev.getReview());
			temp.setStars(rev.getStars());
			temp.setName(rev.getName());
			results.add(temp);
		}
		respond.setResults(results);
		return respond;
	}
	
	public ReviewRepresentation updatedReview(ReviewRequest req) throws UnknownHostException {
		
		Review review = new Review(req.getId());
		review.setReview(req.getReview());
		review.setStars(req.getStars());
		boolean status = review.update();
		if (status) {
			ReviewRepresentation rep = new ReviewRepresentation();
			rep.setOrder(review.getOrder());
			rep.setStars(review.getStars());
			rep.setProduct(review.getProduct());
			rep.setReview(review.getReview());
			rep.setName(review.getName());
			rep.setId(req.getId());
			return rep;
		}
		return new ReviewRepresentation();
	}
	
	public ReviewRepresentation getReview(String id) throws UnknownHostException {
		Review review = new Review(id);
		ReviewRepresentation rep = new ReviewRepresentation();
		rep.setOrder(review.getOrder());
		rep.setStars(review.getStars());
		rep.setProduct(review.getProduct());
		rep.setReview(review.getReview());
		rep.setId(review.getId());
		return rep; 
	}
	
	public boolean deleteReview(String id) throws UnknownHostException {
		ReviewDAO db = ReviewDAO.getInstance();
		return db.deleteReview(id);
	}
	
}
