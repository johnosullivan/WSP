package com.ws.project.customer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import com.mongodb.DBObject;
import com.ws.project.dao.AddressDAO;
import com.ws.project.dao.CustomerDAO;
import com.ws.project.dao.OrderDAO;
import com.ws.project.dao.PhoneDAO;
import com.ws.project.order.Order;
import com.ws.project.payment.Payment;

public class Customer {
	/* attrs */
	private String first;
	private String middle;
	private String last;
	private String id;
	// Added
	private String email;
	private String propicURL;
	//private String phone;
	private String payid;
	private Payment payment;
	
	private ArrayList<CustomerAddress> alladdress;
	private ArrayList<CustomerPhone> allphone;
	
	//Creates the customer in the database
	public String create() throws UnknownHostException {
		CustomerDAO db = CustomerDAO.getInstance();
		if (this.id == null) {
			this.id = db.createCustomer(this);	
			return this.id;
		} 
		return this.id;
	}
	public String addAddress(CustomerAddress add) throws UnknownHostException {
		add.setUser(this.id);
		String newadd = add.save();
		//System.out.println(newadd);
		return newadd;
	}
	public String addPhone(CustomerPhone add) throws UnknownHostException {
		add.setUser(this.id);
		String newadd = add.save();
		///System.out.println(newadd);
		return newadd;
	}
	
	public ArrayList<CustomerAddress> getAllAddress() throws UnknownHostException {
		return this.alladdress;
	}
	
	public ArrayList<CustomerPhone> getAllPhone() throws UnknownHostException {
		return this.allphone;
	}
	
	//Sets and Gets Email
	public void setEmail(String stat) { this.email = stat; }
	public String getEmail() { return this.email; }
	//Sets and Gets Propic
	public void setPP(String stat) { this.propicURL = stat; }
	public String getPP() { return this.propicURL; }
	//Sets and Gets Payment
	public void setPayment(String payid) { 
		
		this.payid = payid; 
	}
	public Payment getPayment() { return this.payment; }
	//Gets all orders created by users
	public ArrayList<Order> getMyOrder() throws UnknownHostException {
		OrderDAO db = OrderDAO.getInstance();
		return db.getMyOrders(this);
	}
	//Return the payment ID to build Payment object or to update link in DB.
	public String getPId() {
		return this.payid;
	}
		
	//Updates the customer data 
	public boolean update() throws UnknownHostException{
		CustomerDAO db = CustomerDAO.getInstance();
		
		return db.updateCustomerById(this);
	}
	//Prints the details of customer
	public void PrintDetail() {
		System.out.println(this.first + " " + this.middle + " " + this.last);
	}
	//Default constructor for blank object.
	public Customer() { 
		this.first = "";
		this.middle = "";
		this.last = "";
		this.payid = "";
		this.email = "";
		//this.phone = "";
		this.propicURL = "";
	}
	//Creates the customer from a document in the database.
	public Customer(String id) throws UnknownHostException {
		//System.out.println("Building: " + id);
		CustomerDAO db = CustomerDAO.getInstance();
		DBObject object = db.findCustomerById(id);
		//System.out.println("Building: " + object);
		this.first = (String)object.get("first");
		this.middle = (String)object.get("middle");
		this.last = (String)object.get("last");
		this.email = (String)object.get("email");
		//this.phone = (String)object.get("phone");
		this.propicURL = (String)object.get("propicURL");
		this.id = id;
		this.payid = (String)object.get("payid");
		this.payment = new Payment(this.payid);
		
		AddressDAO dbaddress = AddressDAO.getInstance();
		this.alladdress =  dbaddress.allAddressFor(this.id);
		
		PhoneDAO dbphone = PhoneDAO.getInstance();
		this.allphone = dbphone.allPhoneFor(this.id);
		
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
