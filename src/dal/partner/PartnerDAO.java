package dal.partner;

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
import model.helper.Helper;
import model.order.Order;
import model.partner.Partner;

public class PartnerDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection partners;
	static DBCollection payments;
	static DBCollection orderlinks;

	private static PartnerDAO instance = null;

	public static PartnerDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new PartnerDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			partners = db.getCollection("partners");
			payments = db.getCollection("payments");
			orderlinks = db.getCollection("orderlinks");
		}
		return instance;
	}

	public ArrayList<Order> getPartnerOrders(Partner part) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.put("partner", part.getID());
		DBCursor cursor = orderlinks.find(query);
		ArrayList<Order> orderlist = new ArrayList<Order>();
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			Order check = new Order((String) s.get("orderid"));
			orderlist.add(check);
		}
		return orderlist;
	}

	// Creates a partner from the Partner object
	public String createPartner(Partner data) {
		String payid = "";
		if (data.getPayment() != null) {
			BasicDBObject newpayment = new BasicDBObject();
			newpayment.append("billing", data.getPayment().getBilling());
			newpayment.append("paytype", Helper.codeForPayment(data.getPayment().getPaymentType()));
			payments.insert(newpayment);
			payid = "" + newpayment.get("_id");
		}
		BasicDBObject newpartner = new BasicDBObject();
		newpartner.append("first", data.getFirst());
		newpartner.append("middle", data.getMiddle());
		newpartner.append("last", data.getLast());
		newpartner.append("email", "");
		newpartner.append("payid", payid);
		newpartner.append("company", data.getCompany());
		newpartner.append("email", data.getEmail());
		//newpartner.append("phone", data.getPhone());
		newpartner.append("propicURL", data.getPP());
		newpartner.append("homepage", data.getHomepage());
		partners.insert(newpartner);
		return "" + newpartner.get("_id");
	}

	// Find the partner from id
	public DBObject findPartnersById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = partners.findOne(query);
		return dbObj;
	}

	// Updates the partner with id
	public boolean updatePartnerById(Partner data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("first", data.getFirst());
		updated.append("middle", data.getMiddle());
		updated.append("last", data.getLast());
		updated.append("email", "");
		updated.append("payid", data.getPId());
		updated.append("company", data.getCompany());
		updated.append("email", data.getEmail());
		//updated.append("phone", data.getPhone());
		updated.append("propicURL", data.getPP());
		updated.append("homepage", data.getHomepage());
		partners.update(new BasicDBObject("_id", new ObjectId(data.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Deletes the partner with id
	public boolean deletePartnerById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		partners.remove(query);
		return true;
	}

}
