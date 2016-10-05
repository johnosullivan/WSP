package com.ws.project.dao;

// Imported Libraries for support
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.ws.project.customer.Customer;
import com.ws.project.order.Order;
import com.ws.project.order.OrderItem;
import com.ws.project.order.Order.OrderStatusType;
import com.ws.project.partner.Partner;
import com.ws.project.payment.Payment;
import com.ws.project.payment.Payment.PaymentType;
import com.ws.project.product.Product;
import com.ws.project.report.Report;
import com.ws.project.review.Review;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import org.bson.types.ObjectId;

// Database object for Object Relation Mapping
public class Database {
	//Create instances for java drivers
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
	//Create instance of the database
	private static Database instance = null;
	public static Database getInstance(){
	  if(instance==null){ instance = new Database(); }
	  return instance;
	}
	//Connects the the database for run queries
	public void connect(boolean cleanstart) throws UnknownHostException {
		mongoClient 		= new MongoClient();
	    db 					= mongoClient.getDB("wsproject");
	    if (cleanstart) {
			this.dropCollection();
		}
	    customers 			= db.getCollection("customers");
	    products 			= db.getCollection("products");
	    partners 			= db.getCollection("partners");
	    orders 				= db.getCollection("orders");
	    orderlinks 			= db.getCollection("orderlinks");
	    shipping 			= db.getCollection("shipping");  
	    user 				= db.getCollection("user");
	    review 				= db.getCollection("review");
	    payments 			= db.getCollection("payments");
	    reports				= db.getCollection("reports");
	}
	//Search for products in product collections
	public ArrayList<Product> searchService(String para) {
		BasicDBObject query = new BasicDBObject();
		Pattern regex = Pattern.compile(para); 
		query.put("name", regex);
		DBCursor cursor = products.find(query);
		ArrayList<Product> search = new ArrayList<Product>();
		while(cursor.hasNext()) {
			DBObject object = cursor.next();
			Product searchresults = new Product();
			searchresults.setName((String)object.get("name"));
			searchresults.setDescription((String)object.get("des"));
			searchresults.setCost((int)object.get("cost"));
			searchresults.setCostCode((String)object.get("curcode"));
			searchresults.setInventory((int)object.get("inventory"));
			searchresults.setSeller((String)object.get("partnerid"));
			search.add(searchresults);
		}
		return search;
	}
	//Cancel the order and updated database
	public boolean cancelledOrder(Order ord) {
		Set<String> ids = new HashSet<String>();
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();		
		while(itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
		    String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
		    list.add(idp);
			ids.add(processedItem.getProduct().getSeller());		
		}
		Iterator<String> partners = ids.iterator();
		while(partners.hasNext()) {
			String partnersid = partners.next();
			BasicDBObject updateorderlink = new BasicDBObject();
			updateorderlink.append("orderid", ord.getID());
			updateorderlink.append("partner", partnersid);
			updateorderlink.append("status", statuscode(ord.getOrderStatus()));
			BasicDBObject searchQuery = new BasicDBObject().append("orderid", ord.getID()).append("partner", partnersid);
			orderlinks.update(searchQuery, updateorderlink);
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", statuscode(ord.getOrderStatus()));
		orders.update(new BasicDBObject("_id", new ObjectId(ord.getID())),new BasicDBObject("$set", neworder));
		return true;
	}
	//Creates a new report
	public String createNewReport(Product product, Date date, int q) {
		BasicDBObject newreport = new BasicDBObject();
		newreport.append("amount",q);
		newreport.append("product", product.getID());
		newreport.append("date", date.toString());
	    reports.insert(newreport);
	    return "" + newreport.get("_id");
	}
	//Create a report
	public Report createReportForProduct(Product product) {
		Report report = new Report();
		BasicDBObject query = new BasicDBObject();
		query.put("product", product.getID());
		DBCursor cursor = reports.find(query);
		while(cursor.hasNext()) {
			DBObject object = cursor.next();
			String str = (int)object.get("amount") + " Sold on: " + (String)object.get("date");
			report.addReport(str);
		}
		return report;
	}
	//Helper funciton for oder status codes
	public int statuscode(OrderStatusType ord) {
		switch(ord) {
			case PROCESSING:
				return 1;
			case PROCESSED:
				return 2;
			case SHIPPED:
				return 3;
			case INROUTE:
				return 4;
			case DELIVERED:
				return 5;
			case CANCELED:
				return 6;
			case PAYMENTFAILED:
				return 7;
			default:
				break;
		}
		return 0;
	}
	//Create order in the database
	public String createOrder(Order ord) {
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();
		Set<String> ids = new HashSet<String>();
		while(itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
			boolean status = processedItem.getProduct().sold(processedItem.getQuantity());
			if (!status) {
				System.out.println("Inventory Understock");
				return "";
			} else {
				ids.add(processedItem.getProduct().getSeller());
				String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
				list.add(idp);
				//System.out.println(idp);
			}	
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", statuscode(ord.getOrderStatus()));
		orders.insert(neworder);
		String orderid = "" + neworder.get("_id");
		Iterator<String> partners = ids.iterator();
		while(partners.hasNext()) {
			BasicDBObject neworderlink = new BasicDBObject();
			neworderlink.append("orderid", orderid);
			neworderlink.append("partner", partners.next());
			neworderlink.append("status", statuscode(ord.getOrderStatus()));
			orderlinks.insert(neworderlink);
		}
		return "" + neworder.get("_id");
	}
	//Gets the my orders for the customers
    public ArrayList<Order> getMyOrders(Customer customer) {
    	BasicDBObject query = new BasicDBObject();
		query.put("customer", customer.getID());
		DBCursor cursor = orders.find(query);
		ArrayList<Order> orderlist = new ArrayList<Order>();
		while(cursor.hasNext()) {
			DBObject s = cursor.next();		    
		    Order check = new Order("" + s.get("_id"));
		    orderlist.add(check);
		}
		return orderlist;
	}
	//Updates the order for customer and partner
	public boolean updateOrder(Order ord) {
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();		
		while(itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
		    String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
		    list.add(idp);
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", statuscode(ord.getOrderStatus()));
		orders.update(new BasicDBObject("_id", new ObjectId(ord.getID())),new BasicDBObject("$set", neworder));
		return true;
	}
	//Gets the order for the products the partner has list in the database.
	public ArrayList<Order> getPartnerOrders(Partner part) {
		BasicDBObject query = new BasicDBObject();
		query.put("partner", part.getID());
		//query.put("status", statuscode(OrderStatusType.PROCESSED));
		DBCursor cursor = orderlinks.find(query);
		ArrayList<Order> orderlist = new ArrayList<Order>();
		while(cursor.hasNext()) {
			DBObject s = cursor.next();		    
		    Order check = new Order((String)s.get("orderid"));
		    orderlist.add(check);
		}
		return orderlist;
	}
	//Finds the order with id
	public DBObject findOrderById(String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = orders.findOne(query);
	    return dbObj;
	}
	//Registers the user in the database for login.
	public boolean registerUser(String username, String password, String email) {
		BasicDBObject newcustomer = new BasicDBObject();
		newcustomer.append("username", username);
		newcustomer.append("password", password);
		newcustomer.append("email", email);
		user.insert(newcustomer);
		return true;
	}
	//Find the user with user name
	public String findUser(String username) {
		DBCursor cursor = user.find();
		while(cursor.hasNext()) {
			DBObject s = cursor.next();
		    return (String)s.get("password");
		}
		return "";
	}
	//Deletes the user with user name
	public void deleteUser(String username) {
		BasicDBObject query = new BasicDBObject();
		query.put("username", username);
		user.remove(query);
	}
	//Deletes the review with a id
	public boolean deleteReview(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("username", id);
		review.remove(query);
		return true;
	}
	//Creates a review from the review object
	public String createReviews(Review data) {
		BasicDBObject newreview = new BasicDBObject();
		newreview.append("review", data.getReview());
		newreview.append("product", data.getProduct());
		newreview.append("order", data.getOrder());
		newreview.append("stars", data.getStars());
	    review.insert(newreview);
	    return "" + newreview.get("_id");
	}
	//Updates the review in the database
	public boolean updateReviewById(Review data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("review", data.getReview());
		updated.append("product", data.getProduct());
		updated.append("order", data.getOrder());
		updated.append("stars", data.getStars());
		review.update(new BasicDBObject("_id", new ObjectId(data.getId())),new BasicDBObject("$set", updated));
		return true;
	}
	//Payment type helper class
	public int codeForPayment(PaymentType type) {
		switch(type) {
		case CC:
			return 1;
		case PAYPAL:
			return 2;
		case BITCOIN:
			return 3;
		default:
			break;
		}
		return 0;
	}
	//Create customer with customer object
	public String createCustomer(Customer data) {
		String payid = "";
		if (data.getPayment() != null) {
			BasicDBObject newpayment = new BasicDBObject();
			newpayment.append("billing", data.getPayment().getBilling());
			newpayment.append("paytype", codeForPayment(data.getPayment().getPaymentType()));
		    payments.insert(newpayment);
		    payid = "" + newpayment.get("_id");
		    //System.out.println("payid:" + payid);
		}
		
		BasicDBObject newcustomer = new BasicDBObject();
		newcustomer.append("first", data.getFirst());
		newcustomer.append("middle", data.getMiddle());
		newcustomer.append("last", data.getLast());
		newcustomer.append("email", data.getEmail());
		newcustomer.append("phone", data.getPhone());
		newcustomer.append("propicURL", data.getPP());
		newcustomer.append("payid", payid);
	    customers.insert(newcustomer);
	    String cusid = "" + newcustomer.get("_id");
	    return cusid;
	}
	//Updates the customer with id in database
	public boolean updateCustomerById(Customer data) { 
		BasicDBObject updated = new BasicDBObject();
		updated.append("first", data.getFirst());
		updated.append("middle", data.getMiddle());
		updated.append("last", data.getLast());
		updated.append("email", data.getEmail());
		updated.append("phone", data.getPhone());
		updated.append("propicURL", data.getPP());
		updated.append("payid", data.getPId());
		customers.update(new BasicDBObject("_id", new ObjectId(data.getID())),new BasicDBObject("$set", updated));
		return true;
	}
	//Deletes the customer with id
	public boolean deleteCustomerById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		customers.remove(query);
		return true;
	}
	//Deletes the order with id
	public boolean deleteOrderById(String id) {
		BasicDBObject querylink = new BasicDBObject();
		querylink.put("orderid", id);
		orderlinks.remove(querylink);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		orders.remove(query);
		return true;
	}
	//Creates a partner from the Partner object
	public String createPartner(Partner data) {
		String payid = "";
		if (data.getPayment() != null) {
			BasicDBObject newpayment = new BasicDBObject();
			newpayment.append("billing", data.getPayment().getBilling());
			newpayment.append("paytype", codeForPayment(data.getPayment().getPaymentType()));
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
		newpartner.append("phone", data.getPhone());
		newpartner.append("propicURL", data.getPP());
		newpartner.append("homepage", data.getHomepage());
		partners.insert(newpartner);
	    return "" + newpartner.get("_id");
	}
	//Updates the partner with id
	public boolean updatePartnerById(Partner data) { 
		BasicDBObject updated = new BasicDBObject();
		updated.append("first", data.getFirst());
		updated.append("middle", data.getMiddle());
		updated.append("last", data.getLast());
		updated.append("email", "");
		updated.append("payid", data.getPId());
		updated.append("company", data.getCompany());
		updated.append("email", data.getEmail());
		updated.append("phone", data.getPhone());
		updated.append("propicURL", data.getPP());
		updated.append("homepage", data.getHomepage());
		partners.update(new BasicDBObject("_id", new ObjectId(data.getID())),new BasicDBObject("$set", updated));
		return true;
	}
	//Deletes the partner with id
	public boolean deletePartnerById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		partners.remove(query);
		return true;
	}
	//Creates a product from the product object
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
	//Updates the product from id
	public boolean updateProductById(Product data) {
		BasicDBObject updated = new BasicDBObject();
		updated.append("name", data.getName());
		updated.append("des", data.getDescription());
		updated.append("cost", data.getCost());
		updated.append("inventory", data.getInventory());
		updated.append("costcode", data.getCostCode());
		updated.append("partnerid", data.getSeller());
		products.update(new BasicDBObject("_id", new ObjectId(data.getID())),new BasicDBObject("$set", updated));
		return true;
	}
	//Deletes the product from id
	public boolean deleteProductById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		products.remove(query);
		return true;
	}
	//Find the customer from id
	public DBObject findCustomerById(String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = customers.findOne(query);
	    return dbObj;
	}
	//Find the customer from id
	public DBObject findPaymentById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = payments.findOne(query);
		return dbObj;
	}
	//Find the product from id
	public DBObject findProductsById(String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = products.findOne(query);
	    return dbObj;
	}
	//Find the partner from id
	public DBObject findPartnersById(String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = partners.findOne(query);
	    return dbObj;
	}
	//Find the review from id
	public DBObject findReviewById(String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = review.findOne(query);
	    return dbObj;
	}
	//Close the database
	public void close() {
		mongoClient.close();
	}
	//Cleans the database by dropping collection
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
	}
}
