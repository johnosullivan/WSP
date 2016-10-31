package dal.customer;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.customer.Customer;
import model.helper.Helper;

public class CustomerDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection customers;
	static DBCollection payments;

	private static CustomerDAO instance = null;

	public static CustomerDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new CustomerDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			customers = db.getCollection("customers");
			payments = db.getCollection("payments");
		}
		return instance;
	}

	// Create customer with customer object
	public String createCustomer(Customer data) {
		String payid = "";
		System.out.println("Create the customer");
		if (data.getPayment() != null) {
			BasicDBObject newpayment = new BasicDBObject();
			newpayment.append("billing", data.getPayment().getBilling());
			newpayment.append("paytype", Helper.codeForPayment(data.getPayment().getPaymentType()));
			payments.insert(newpayment);
			payid = "" + newpayment.get("_id");
		}

		BasicDBObject newcustomer = new BasicDBObject();
		newcustomer.append("first", data.getFirst());
		newcustomer.append("middle", data.getMiddle());
		newcustomer.append("last", data.getLast());
		newcustomer.append("email", data.getEmail());
		//newcustomer.append("phone", data.getPhone());
		newcustomer.append("propicURL", data.getPP());
		newcustomer.append("payid", payid);
		customers.insert(newcustomer);
		String cusid = "" + newcustomer.get("_id");
		return cusid;
	}

	// Updates the customer with id in database
	public boolean updateCustomerById(Customer data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("first", data.getFirst());
		updated.append("middle", data.getMiddle());
		updated.append("last", data.getLast());
		updated.append("email", data.getEmail());
		//updated.append("phone", data.getPhone());
		updated.append("propicURL", data.getPP());
		updated.append("payid", data.getPId());
		customers.update(new BasicDBObject("_id", new ObjectId(data.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	public DBObject findCustomerById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = customers.findOne(query);
		return dbObj;
	}

	// Deletes the customer with id
	public boolean deleteCustomerById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		customers.remove(query);
		return true;
	}

}
