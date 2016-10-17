package com.ws.project.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class PaymentDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection payments;

	private static PaymentDAO instance = null;

	public static PaymentDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new PaymentDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			payments = db.getCollection("payments");
		}
		return instance;
	}

	// Find the customer from id
	public DBObject findPaymentById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = payments.findOne(query);
		return dbObj;
	}
}
