package dal.billing;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.billing.Billing;

public class BillingDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection billing;

	private static BillingDAO instance = null;

	public static BillingDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new BillingDAO();
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
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
