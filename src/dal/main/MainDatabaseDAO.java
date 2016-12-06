package dal.main;

// Imported Libraries for support
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.customer.Customer;
import model.partner.Partner;
import model.product.Product;
import service.user.representation.Account;

import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.regex.Pattern;

import org.bson.types.ObjectId;

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
		mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
		db = mongoClient.getDB(Configs.db);
		//mongoClient = new MongoClient();
		//db = mongoClient.getDB("wsproject");
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
	
	public Account login(String username,String password) throws UnknownHostException {
		
		BasicDBObject query = new BasicDBObject();
		query.put("username", username);
		query.put("password", password);
		DBObject dbObj = user.findOne(query);
		
		Account acc = new Account();
		System.out.println(dbObj.get("type"));
		System.out.println(dbObj.get("typeid"));
		int t = (int)dbObj.get("type");
		String id = (String)dbObj.get("typeid");
		if (t == 0) {
			Customer cus = new Customer(id);
			acc.setFirst(cus.getFirst());
			acc.setMiddle(cus.getMiddle());
			acc.setLast(cus.getLast());
			acc.setEmail(cus.getEmail());
			acc.setId(id);
			acc.setType(t);
			acc.setCompany("");
			acc.setHomepage("");
		}
		if (t == 1) {
			Partner cus = new Partner(id);
			acc.setFirst(cus.getFirst());
			acc.setMiddle(cus.getMiddle());
			acc.setLast(cus.getLast());
			acc.setEmail(cus.getEmail());
			acc.setId(id);
			acc.setType(t);
			acc.setCompany(cus.getCompany());
			acc.setHomepage(cus.getHomepage());
		}
		
		
		return acc;
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
	
	public boolean register(String username, String password, String email,int type, String id) {
		BasicDBObject newcustomer = new BasicDBObject();
		newcustomer.append("username", username);
		newcustomer.append("password", password);
		newcustomer.append("email", email);
		newcustomer.append("type", type);
		newcustomer.append("typeid", id);
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
