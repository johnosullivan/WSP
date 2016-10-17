package com.ws.project.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.ws.project.review.Review;

public class ReviewDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection review;

	private static ReviewDAO instance = null;

	public static ReviewDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new ReviewDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			review = db.getCollection("review");
		}
		return instance;
	}

	// Find the review from id
	public DBObject findReviewById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = review.findOne(query);
		return dbObj;
	}

	// Deletes the review with a id
	public boolean deleteReview(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("username", id);
		review.remove(query);
		return true;
	}

	// Creates a review from the review object
	public String createReviews(Review data) {
		BasicDBObject newreview = new BasicDBObject();
		newreview.append("review", data.getReview());
		newreview.append("product", data.getProduct());
		newreview.append("order", data.getOrder());
		newreview.append("stars", data.getStars());
		review.insert(newreview);
		return "" + newreview.get("_id");
	}

	// Updates the review in the database
	public boolean updateReviewById(Review data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("review", data.getReview());
		updated.append("product", data.getProduct());
		updated.append("order", data.getOrder());
		updated.append("stars", data.getStars());
		review.update(new BasicDBObject("_id", new ObjectId(data.getId())), new BasicDBObject("$set", updated));
		return true;
	}
}
