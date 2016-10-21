package com.ws.project.customer;

import java.net.UnknownHostException;

import com.mongodb.DBObject;
//import com.ws.project.dao.AddressDAO;
import com.ws.project.dao.PhoneDAO;

public class CustomerPhone {

	private String type;
	private String phone;
	private String id;
	private String user;
	
	public void setType(String type) { this.type = type; }
	public String getType() { return this.type; }
	
	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }
	
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }
	
	public void setID(String id) { this.id = id; }
	public String getID() { return this.id; }
	
	public CustomerPhone() {
		this.type = "";
		this.phone = "";
	}
	
	public String save() throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		return db.createPhone(this);
	}
	
	public CustomerPhone(String id) {
		PhoneDAO db = new PhoneDAO();
		DBObject object = db.findPhoneById(id);
		this.type = (String)object.get("type");
		this.phone = (String)object.get("phone");
		this.id = id;
		this.user = (String)object.get("user");
	}
	
	
}
