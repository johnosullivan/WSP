package com.ws.project.dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.ws.project.billing.Billing;

public class BillingDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection billing;

	private static BillingDAO instance = null;

	public static BillingDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new BillingDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			billing = db.getCollection("billing");
		}
		return instance;
	}
	
	public String createBilling(Billing add) {
		BasicDBObject newa = new BasicDBObject();
		newa.append("billing", add.getAddress().getID());
		billing.insert(newa);
		String id = "" + newa.get("_id");
		return id;
	}
	
}
