package com.ws.project.partner;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
import com.ws.project.dao.AddressDAO;

public class PartnerAddress {
	// attrs
	private String address;
	private String city;
	private String state;
	private String id;
	private String user;
	private int zip;
	// Get and Set methods
	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }
	public void setUser(String user) { 	 this.user = user; }
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
	public PartnerAddress() {
		this.address = "";
		this.city = "";
		this.state = "";
		this.zip = 0;
		
	}
	// Returns the full address string
	public String full() {
		return this.address + " " + this.city + " " + this.state + " " + this.zip;
	}
	// Custom constructor
	public PartnerAddress(String id) {
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
		return db.createAddresPartner(this);
	}
}
