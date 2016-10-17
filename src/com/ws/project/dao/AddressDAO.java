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
import com.ws.project.address.Address;


public class AddressDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection address;

	private static AddressDAO instance = null;

	public static AddressDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new AddressDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			address = db.getCollection("address");
		}
		return instance;
	}

	public String createAddres(Address add) {
		BasicDBObject newa = new BasicDBObject();
		newa.append("address", add.getAddress());
		newa.append("city", add.getCity());
		newa.append("state", add.getState());
		newa.append("zip", add.getZip());
		newa.append("user", add.getUser());
		address.insert(newa);
		String id = "" + newa.get("_id");
		return id;
	}
	
	public ArrayList<Address> allAddressFor(String para) {
		ArrayList<Address> array = new ArrayList<Address>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = address.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			Address temp = new Address();
			temp.setAddress("" + (String)s.get("address"));
			temp.setCity("" + (String)s.get("city"));
			temp.setState("" + (String)s.get("state"));
			temp.setZip((int)s.get("zip"));
			array.add(temp);
		}
		return array;
	}


	// Find the address from id
	public DBObject findAddressById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = address.findOne(query);
		return dbObj;
	}

}
