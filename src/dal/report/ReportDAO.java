package dal.report;

import java.net.UnknownHostException;
import java.sql.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dal.Configs;
import model.product.Product;
import model.report.Report;

public class ReportDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection products;
	static DBCollection reports;

	private static ReportDAO instance = null;

	public static ReportDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new ReportDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			products = db.getCollection("products");
			reports = db.getCollection("reports");
		}
		return instance;
	}

	// Creates a new report
	public String createNewReport(Product product, Date date, int q) {
		BasicDBObject newreport = new BasicDBObject();
		newreport.append("amount", q);
		newreport.append("product", product.getID());
		newreport.append("date", date.toString());
		reports.insert(newreport);
		return "" + newreport.get("_id");
	}

	// Create a report
	public Report createReportForProduct(Product product) {
		Report report = new Report();
		BasicDBObject query = new BasicDBObject();
		query.put("product", product.getID());
		DBCursor cursor = reports.find(query);
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			String str = (int) object.get("amount") + " Sold on: " + (String) object.get("date");
			report.addReport(str);
		}
		return report;
	}
}
