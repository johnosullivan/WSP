package dal.product;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;

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

public class ProductDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection products;
	static DBCollection reports;

	private static ProductDAO instance = null;

	public static ProductDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new ProductDAO();
			mongoClient = new MongoClient(new MongoClientURI(Configs.connect));
			db = mongoClient.getDB(Configs.db);
			//mongoClient = new MongoClient();
			//db = mongoClient.getDB("wsproject");
			products = db.getCollection("products");
			reports = db.getCollection("reports");
		}
		return instance;
	}

	// Find the product from id
	public DBObject findProductsById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = products.findOne(query);
		return dbObj;
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

	public ArrayList<Product> getPartnerProducts(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("partnerid", id);
		DBCursor cursor = products.find(query);
		ArrayList<Product> pproduct = new ArrayList<Product>();
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
			pproduct.add(pro);
		}
		return pproduct;
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

	// Creates a product from the product object
	public String createProduct(Product data) {
		BasicDBObject newproduct = new BasicDBObject();
		newproduct.append("name", data.getName());
		newproduct.append("des", data.getDescription());
		newproduct.append("cost", data.getCost());
		newproduct.append("inventory", data.getInventory());
		newproduct.append("costcode", data.getCostCode());
		newproduct.append("partnerid", data.getSeller());
		products.insert(newproduct);
		return "" + newproduct.get("_id");
	}

	// Updates the product from id
	public boolean updateProductById(Product data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("name", data.getName());
		updated.append("des", data.getDescription());
		updated.append("cost", data.getCost());
		updated.append("inventory", data.getInventory());
		updated.append("costcode", data.getCostCode());
		updated.append("partnerid", data.getSeller());
		products.update(new BasicDBObject("_id", new ObjectId(data.getID())), new BasicDBObject("$set", updated));
		return true;
	}

	// Deletes the product from id
	public boolean deleteProductById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		products.remove(query);
		return true;
	}
}
