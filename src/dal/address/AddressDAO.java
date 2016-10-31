package dal.address;

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
import model.billing.BillingAddress;
import model.customer.CustomerAddress;
import model.order.ShippingAddress;
import model.partner.PartnerAddress;

public class AddressDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection address;

	private static AddressDAO instance = null;

	public static AddressDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new AddressDAO();
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			address = db.getCollection("address");
		}
		return instance;
	}

	public String createAddres(CustomerAddress add) {
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

	public String createAddresBilling(BillingAddress add) {
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

	public String createAddresShipping(ShippingAddress add) {
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

	public String createAddresPartner(PartnerAddress add) {
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

	// Updates the customer with id in database
	public boolean updateCustomerAddressById(CustomerAddress add) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("address", add.getAddress());
		updated.append("city", add.getCity());
		updated.append("state", add.getState());
		updated.append("zip", add.getZip());
		updated.append("user", add.getUser());
		address.update(new BasicDBObject("_id", new ObjectId(add.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Updates the customer with id in database
	public boolean updatePartnerAddressById(PartnerAddress add) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("address", add.getAddress());
		updated.append("city", add.getCity());
		updated.append("state", add.getState());
		updated.append("zip", add.getZip());
		updated.append("user", add.getUser());
		address.update(new BasicDBObject("_id", new ObjectId(add.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Deletes the customer with id
	public boolean deleteAddressById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		address.remove(query);
		return true;
	}

	public ArrayList<CustomerAddress> allAddressFor(String para) {
		ArrayList<CustomerAddress> array = new ArrayList<CustomerAddress>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = address.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			System.out.println(s);
			CustomerAddress temp = new CustomerAddress();
			temp.setAddress("" + (String) s.get("address"));
			temp.setCity("" + (String) s.get("city"));
			temp.setState("" + (String) s.get("state"));
			String u = (String)s.get("user");
			ObjectId id = (ObjectId)s.get( "_id" );
			temp.setUser(u);
			temp.setID(id.toString());
			temp.setZip((int) s.get("zip"));
			array.add(temp);
		}
		return array;
	}

	public ArrayList<PartnerAddress> allAddressForPartner(String para) {
		ArrayList<PartnerAddress> array = new ArrayList<PartnerAddress>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = address.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			PartnerAddress temp = new PartnerAddress();
			temp.setAddress("" + (String) s.get("address"));
			temp.setCity("" + (String) s.get("city"));
			temp.setState("" + (String) s.get("state"));
			temp.setID("" + (String) s.get("_id"));
			temp.setUser("" + (String) s.get("user"));
			temp.setZip((int) s.get("zip"));
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
