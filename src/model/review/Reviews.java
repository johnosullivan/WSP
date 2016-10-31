package model.review;

import java.net.UnknownHostException;
import java.util.ArrayList;

import dal.review.ReviewDAO;

public class Reviews {

	public ArrayList<Review> getReviewsWithId(String id) throws UnknownHostException {
		ReviewDAO db = ReviewDAO.getInstance();
		ArrayList<Review> data = db.reviewsForProductId(id);
		return data;
	}
	
}
