package dal.payment;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.helper.Helper;
import model.payment.Payment;

public class PaymentDAO {

	static MongoClient mongoClient;
	static DB db;
	static DBCollection payments;

	private static PaymentDAO instance = null;

	public static PaymentDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new PaymentDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			payments = db.getCollection("payments");
		}
		return instance;
	}
	public boolean deletePaymentById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		payments.remove(query);
		return true;
	}
	
	public boolean updatePaymentById(Payment data, String id) {
		BasicDBObject newpayment = new BasicDBObject();
		newpayment.append("billing", data.getBilling());
		newpayment.append("paytype", Helper.codeForPayment(data.getPaymentType()));
		newpayment.append("ccnum", data.getCCnum());
		newpayment.append("ccexp", data.getCCexp());
		newpayment.append("ccsec", data.getCCsec());
		newpayment.append("ppacc", data.getPPacc());
		newpayment.append("btacc", data.getBTacc());
		payments.update(new BasicDBObject("_id", new ObjectId(id)), new BasicDBObject("$set", newpayment));
		return true;
	}
	
	// Create customer with customer object
	public String createPayment(Payment data) {	
		BasicDBObject newpayment = new BasicDBObject();
		newpayment.append("billing", data.getBilling());
		newpayment.append("paytype", Helper.codeForPayment(data.getPaymentType()));
		newpayment.append("ccnum", data.getCCnum());
		newpayment.append("ccexp", data.getCCexp());
		newpayment.append("ccsec", data.getCCsec());
		newpayment.append("ppacc", data.getPPacc());
		newpayment.append("btacc", data.getBTacc());
		payments.insert(newpayment);	
		return "" + newpayment.get("_id");
	}
	
	// Find the customer from id
	public DBObject findPaymentById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = payments.findOne(query);
		return dbObj;
	}
}
