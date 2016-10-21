package com.ws.project.partner;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
import com.ws.project.dao.PhoneDAO;

public class PartnerPhone {
	// attrs	
	private String type;
	private String phone;
	private String id;
	private String user;
	// Get and Set
	public void setType(String type) { this.type = type; }
	public String getType() { return this.type; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }
	public void setID(String id) { this.id = id; }
	public String getID() { return this.id; }
	// Default constructor
	public PartnerPhone() {
		this.type = "";
		this.phone = "";
	}
	// Saves the address
	public String save() throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		return db.createPhonePartner(this);
	}
	// Custom constructor
	public PartnerPhone(String id) {
		PhoneDAO db = new PhoneDAO();
		DBObject object = db.findPhoneById(id);
		this.type = (String)object.get("type");
		this.phone = (String)object.get("phone");
		this.id = id;
		this.user = (String)object.get("user");
	}
}
