package com.ws.project.dao;

// Imported Libraries for support
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import com.ws.project.product.Product;

import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.regex.Pattern;

// Database object for Object Relation Mapping
public class MainDatabaseDAO {
	// Create instances for java drivers
	MongoClient mongoClient;
	DB db;
	DBCollection customers;
	DBCollection products;
	DBCollection partners;
	DBCollection orders;
	DBCollection orderlinks;
	DBCollection shipping;
	DBCollection user;
	DBCollection review;
	DBCollection payments;
	DBCollection reports;
	DBCollection phone;
	DBCollection address;
	// Create instance of the database
	private static MainDatabaseDAO instance = null;

	public static MainDatabaseDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new MainDatabaseDAO();
			instance.connect(true);
		}
		return instance;
	}

	// Connects the the database for run queries
	public void connect(boolean cleanstart) throws UnknownHostException {
		mongoClient = new MongoClient();
		db = mongoClient.getDB("wsproject");
		customers = db.getCollection("customers");
		products = db.getCollection("products");
		partners = db.getCollection("partners");
		orders = db.getCollection("orders");
		orderlinks = db.getCollection("orderlinks");
		shipping = db.getCollection("shipping");
		user = db.getCollection("user");
		review = db.getCollection("review");
		payments = db.getCollection("payments");
		reports = db.getCollection("reports");
		phone = db.getCollection("phone");
		address = db.getCollection("address");
	}

	// Search for products in product collections
	public ArrayList<Product> searchService(String para) {
		BasicDBObject query = new BasicDBObject();
		Pattern regex = Pattern.compile(para);
		query.put("name", regex);
		DBCursor cursor = products.find(query);
		ArrayList<Product> search = new ArrayList<Product>();
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			Product pro = new Product();
			pro.setID("" + object.get("_id"));
			pro.setName((String)object.get("name"));
			pro.setDescription((String)object.get("des"));
			pro.setCost((int)object.get("cost"));
			pro.setCostCode((String)object.get("costcode"));
			pro.setInventory((int)object.get("inventory"));
			pro.setSeller((String)object.get("partnerid"));
			search.add(pro);
		}
		return search;
	}

	// Deletes the user with user name
	public void deleteUser(String username) {
		BasicDBObject query = new BasicDBObject();
		query.put("username", username);
		user.remove(query);
	}

	// Find the user with user name
	public String findUser(String username) {
		DBCursor cursor = user.find();
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			return (String) s.get("password");
		}
		return "";
	}

	// Registers the user in the database for login.
	public boolean registerUser(String username, String password, String email) {
		BasicDBObject newcustomer = new BasicDBObject();
		newcustomer.append("username", username);
		newcustomer.append("password", password);
		newcustomer.append("email", email);
		user.insert(newcustomer);
		return true;
	}

	// Close the database
	public void close() {
		mongoClient.close();
	}

	// Cleans the database by dropping collection
	public void dropCollection() {
		products.drop();
		user.drop();
		partners.drop();
		customers.drop();
		shipping.drop();
		orders.drop();
		orderlinks.drop();
		review.drop();
		payments.drop();
		reports.drop();
		phone.drop();
		address.drop();
	}
}
