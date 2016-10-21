package com.ws.project.order;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
import com.ws.project.dao.AddressDAO;

public class ShippingAddress {
	// attrs
	private String address;
	private String city;
	private String state;
	private String id;
	private String user;
	private int zip;
	// Get and Set	
	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }
	public void setUser(String user) { 	this.user = user; }
	public String getUser() { return this.user; }
	public void setID(String id) { this.id = id; }
	public String getID() { return this.id; }
	public void setCity(String city) { this.city = city; }
	public String getCity() { return this.city; }
	public void setState(String state) { this.state = state; }
	public String getState() { return this.state; }
	public void setZip(int zip) { this.zip = zip; }
	public int getZip() { return this.zip; }
	// Default constructor
	public ShippingAddress() {
		this.address = "";
		this.city = "";
		this.state = "";
		this.zip = 0;
		
	}
	// Full address for shipping
	public String full() {
		return this.address + " " + this.city + " " + this.state + " " + this.zip;
	}
	// Custom constructor
	public ShippingAddress(String id) {
		AddressDAO db = new AddressDAO();
		DBObject object = db.findAddressById(id);
		this.address = (String)object.get("address");
		this.city = (String)object.get("city");
		this.state = (String)object.get("state");
		this.zip = (int)object.get("zip");
		this.id = id;
		this.user = (String)object.get("user");
	}
	// Saves the address
	public String save() throws UnknownHostException {
		AddressDAO db = AddressDAO.getInstance();
		return db.createAddresShipping(this);
	}
	
}
