package dal.phone;

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
import model.customer.CustomerPhone;
import model.partner.PartnerPhone;

public class PhoneDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection phone;

	private static PhoneDAO instance = null;

	public static PhoneDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new PhoneDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			phone = db.getCollection("phone");
		}
		return instance;
	}
	
	public boolean deletePhoneById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		phone.remove(query);
		return true;
	}

	public String createPhone(CustomerPhone phon) {
		BasicDBObject newa = new BasicDBObject();
		newa.append("type", phon.getType());
		newa.append("phone", phon.getPhone());
		newa.append("user", phon.getUser());
		phone.insert(newa);
		String id = "" + newa.get("_id");
		return id;
	}

	public String createPhonePartner(PartnerPhone phon) {
		BasicDBObject newa = new BasicDBObject();
		newa.append("type", phon.getType());
		newa.append("phone", phon.getPhone());
		newa.append("user", phon.getUser());
		phone.insert(newa);
		String id = "" + newa.get("_id");
		return id;
	}

	public ArrayList<CustomerPhone> allPhoneFor(String para) {
		ArrayList<CustomerPhone> array = new ArrayList<CustomerPhone>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = phone.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			CustomerPhone temp = new CustomerPhone();
			temp.setPhone("" + (String) s.get("phone"));
			temp.setType("" + (String) s.get("type"));
			String u = (String)s.get("user");
			ObjectId id = (ObjectId)s.get( "_id" );
			temp.setUser(u);
			temp.setID(id.toString());
			array.add(temp);
		}
		return array;
	}

	public ArrayList<PartnerPhone> allPhoneForPartner(String para) {
		ArrayList<PartnerPhone> array = new ArrayList<PartnerPhone>();
		BasicDBObject query = new BasicDBObject();
		query.put("user", para);
		DBCursor cursor = phone.find(query);
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			PartnerPhone temp = new PartnerPhone();
			temp.setPhone("" + (String) s.get("phone"));
			temp.setType("" + (String) s.get("type"));
			array.add(temp);
		}
		return array;
	}

	// Updates the customer with id in database
	public boolean updateCustomerPhoneById(CustomerPhone phon) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("type", phon.getType());
		updated.append("phone", phon.getPhone());
		updated.append("user", phon.getUser());
		phone.update(new BasicDBObject("_id", new ObjectId(phon.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Updates the customer with id in database
	public boolean updatePartnerPhoneById(PartnerPhone phon) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("type", phon.getType());
		updated.append("phone", phon.getPhone());
		updated.append("user", phon.getUser());
		phone.update(new BasicDBObject("_id", new ObjectId(phon.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Find the address from id
	public DBObject findPhoneById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = phone.findOne(query);
		return dbObj;
	}

}
