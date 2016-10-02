package com.ws.project;

import java.util.ArrayList;
import com.mongodb.DBObject;

public class Customer {
	/* attrs */
	private String first;
	private String middle;
	private String last;
	private String id;
	// Added
	private String email;
	private String propicURL;
	private String phone;
	private String payid;
	private Payment payment;
	//Creates the customer in the database
	public String create() {
		Database db = Database.getInstance();
		if (id == null) {
			id = db.createCustomer(this);
			return id;
		} 
		return id;
	}
	//Sets and Gets Email
	public void setEmail(String stat) { this.email = stat; }
	public String getEmail() { return this.email; }
	//Sets and Gets Propic
	public void setPP(String stat) { this.propicURL = stat; }
	public String getPP() { return this.propicURL; }
	//Sets and Gets Phone
	public void setPhone(String stat) { this.phone = stat; }
	public String getPhone() { return this.phone; }
	//Sets and Gets Payment
	public void setPayment(Payment stat) { this.payment = stat; }
	public Payment getPayment() { return this.payment; }
	//Gets all orders created by users
	public ArrayList<Order> getMyOrder() {
		Database db = Database.getInstance();
		return db.getMyOrders(this);
	}
	public String getPId() {
		return this.payid;
	}
	//Updates the customer data
	public boolean update() {
		Database db = Database.getInstance();
		db.updateCustomerById(this);
		return true;
	}
	//Prints the details of customer
	public void PrintDetail() {
		System.out.println(this.first + " " + this.middle + " " + this.last);
	}
	public Customer() { 
		this.first = "";
		this.middle = "";
		this.last = "";
		this.payid = "";
		this.email = "";
		this.phone = "";
		this.propicURL = "";
	}
	//Creates the customer from a document in the database,
	public Customer(String id) {
		Database db = Database.getInstance();
		DBObject object = db.findCustomerById(id);
		this.first = (String)object.get("first");
		this.middle = (String)object.get("middle");
		this.last = (String)object.get("last");
		this.email = (String)object.get("email");
		this.phone = (String)object.get("phone");
		this.propicURL = (String)object.get("propicURL");
		this.id = id;
		this.payid = (String)object.get("payid");
		this.payment = new Payment(this.payid);
	}
	//Gets the customer id
	public String getID() { return this.id; }
	//Gets the full name of the customer
	public String fullName() {
		return this.first + " " + this.middle + " " + this.last;
	}
	//Sets and get the first name
	public void setFirst(String fir) { this.first = fir; }
	public String getFirst() { return this.first; }
	//Sets and get the middle name
	public void setMiddle(String mid) { this.middle = mid; }
	public String getMiddle() { return this.middle; }
	//Sets and get the last name
	public void setLast(String las) { this.last = las; }
	public String getLast() { return this.last; }
}
