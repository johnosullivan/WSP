package com.ws.project.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.ws.project.phone.Phone;

public class PhoneDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection phone;

	private static PhoneDAO instance = null;

	public static PhoneDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new PhoneDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			phone = db.getCollection("phone");
		}
		return instance;
	}
	
	public String createPhone(Phone phon) {
		BasicDBObject newa = new BasicDBObject();
		newa.append("type", phon.getType());
		newa.append("phone", phon.getPhone());
		newa.append("user", phon.getUser());
		phone.insert(newa);
		String id = "" + newa.get("_id");
		return id;
	}
	
	public ArrayList<Phone> allPhoneFor(String para) {
		ArrayList<Phone> array = new ArrayList<Phone>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = phone.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			Phone temp = new Phone();
			temp.setPhone("" + (String)s.get("phone"));
			temp.setType("" + (String)s.get("type"));
			array.add(temp);
		}
		return array;
	}

	// Find the address from id
	public DBObject findPhoneById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = phone.findOne(query);
		return dbObj;
	}
	
}
