package com.ws.project.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.ws.project.customer.Customer;
import com.ws.project.helper.Helper;
import com.ws.project.order.Order;
import com.ws.project.order.OrderItem;

public class OrderDAO {
	static MongoClient mongoClient;
	static DB db;
	static DBCollection orders;
	static DBCollection orderlinks;

	private static OrderDAO instance = null;

	public static OrderDAO getInstance() throws UnknownHostException {
		if (instance == null) {
			instance = new OrderDAO();
			mongoClient = new MongoClient();
			db = mongoClient.getDB("wsproject");
			orders = db.getCollection("orders");
			orderlinks = db.getCollection("orderlinks");
		}
		return instance;
	}

	public boolean deleteOrderById(String id) {
		BasicDBObject querylink = new BasicDBObject();
		querylink.put("orderid", id);
		orderlinks.remove(querylink);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		orders.remove(query);
		return true;
	}

	// Finds the order with id
	public DBObject findOrderById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject dbObj = orders.findOne(query);
		return dbObj;
	}

	// Create order in the database
	public String createOrder(Order ord) throws UnknownHostException {
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();
		Set<String> ids = new HashSet<String>();
		while (itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
			boolean status = processedItem.getProduct().sold(processedItem.getQuantity());
			if (!status) {
				System.out.println("Inventory Understock");
				return "";
			} else {
				ids.add(processedItem.getProduct().getSeller());
				String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
				list.add(idp);
				// System.out.println(idp);
			}
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", Helper.statuscode(ord.getOrderStatus()));
		orders.insert(neworder);
		String orderid = "" + neworder.get("_id");
		Iterator<String> partners = ids.iterator();
		while (partners.hasNext()) {
			BasicDBObject neworderlink = new BasicDBObject();
			neworderlink.append("orderid", orderid);
			neworderlink.append("partner", partners.next());
			neworderlink.append("status", Helper.statuscode(ord.getOrderStatus()));
			orderlinks.insert(neworderlink);
		}
		return "" + neworder.get("_id");
	}

	// Gets the my orders for the customers
	public ArrayList<Order> getMyOrders(Customer customer) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.put("customer", customer.getID());
		DBCursor cursor = orders.find(query);
		ArrayList<Order> orderlist = new ArrayList<Order>();
		while (cursor.hasNext()) {
			DBObject s = cursor.next();
			Order check = new Order("" + s.get("_id"));
			orderlist.add(check);
		}
		return orderlist;
	}

	// Updates the order for customer and partner
	public boolean updateOrder(Order ord) {
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();
		while (itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
			String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
			list.add(idp);
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", Helper.statuscode(ord.getOrderStatus()));
		orders.update(new BasicDBObject("_id", new ObjectId(ord.getID())), new BasicDBObject("$set", neworder));
		return true;
	}

	// Cancel the order and updated database
	public boolean cancelledOrder(Order ord) {
		Set<String> ids = new HashSet<String>();
		Iterator<OrderItem> itorder = ord.getProducts().iterator();
		ArrayList<String> list = new ArrayList<String>();
		while (itorder.hasNext()) {
			OrderItem processedItem = itorder.next();
			String idp = processedItem.getProduct().getID() + "_" + processedItem.getQuantity();
			list.add(idp);
			ids.add(processedItem.getProduct().getSeller());
		}
		Iterator<String> partners = ids.iterator();
		while (partners.hasNext()) {
			String partnersid = partners.next();
			BasicDBObject updateorderlink = new BasicDBObject();
			updateorderlink.append("orderid", ord.getID());
			updateorderlink.append("partner", partnersid);
			updateorderlink.append("status", Helper.statuscode(ord.getOrderStatus()));
			BasicDBObject searchQuery = new BasicDBObject().append("orderid", ord.getID()).append("partner",
					partnersid);
			orderlinks.update(searchQuery, updateorderlink);
		}
		BasicDBObject neworder = new BasicDBObject();
		neworder.append("comfirmnumber", ord.getConfirmNumber());
		neworder.append("customer", ord.getBuyer().getID());
		neworder.append("products", list);
		neworder.append("shipping", ord.getShipping());
		neworder.append("status", Helper.statuscode(ord.getOrderStatus()));
		orders.update(new BasicDBObject("_id", new ObjectId(ord.getID())), new BasicDBObject("$set", neworder));
		return true;
	}
}
