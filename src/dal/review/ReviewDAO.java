package dal.review;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.review.Review;

public class ReviewDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection review;

	private static ReviewDAO instance = null;

	public static ReviewDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new ReviewDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			review = db.getCollection("review");
		}
		return instance;
	}
	
	public ArrayList<Review> reviewsForProductId(String para) {
		BasicDBObject query = new BasicDBObject();
		query.put("product", para);
		DBCursor cursor = review.find(query);
		ArrayList<Review> search = new ArrayList<Review>();
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			Review rew = new Review();
			rew.setReview((String)object.get("review"));
			rew.setName((String)object.get("name"));
			rew.setStars((int)object.get("stars"));
			search.add(rew);
		}
		return search;
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
		query.put("_id", new ObjectId(id));
		review.remove(query);
		return true;
	}

	// Creates a review from the review object
	public String createReviews(Review data) {
		BasicDBObject newreview = new BasicDBObject();
		newreview.append("review", data.getReview());
		newreview.append("product", data.getProduct());
		newreview.append("order", data.getOrder());
		newreview.append("name", data.getName());
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
		updated.append("name", data.getName());
		review.update(new BasicDBObject("_id", new ObjectId(data.getId())), new BasicDBObject("$set", updated));
		return true;
	}
}
