package com.ws.project.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.ws.project.helper.Helper;
import com.ws.project.payment.Payment;

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

	// Create customer with customer object
	public String createPayment(Payment data) {	
		BasicDBObject newpayment = new BasicDBObject();
		newpayment.append("billing", data.getBilling());
		newpayment.append("paytype", Helper.codeForPayment(data.getPaymentType()));
		payments.insert(newpayment);	
		return "" + newpayment.get("_id");
	}
	
	// Find the customer from id
	public DBObject findPaymentById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = payments.findOne(query);
		return dbObj;
	}
}
