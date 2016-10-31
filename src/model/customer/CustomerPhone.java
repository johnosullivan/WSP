package model.customer;

import java.net.UnknownHostException;

import com.mongodb.DBObject;

import dal.phone.PhoneDAO;

public class CustomerPhone {
	// attrs
	private String type;
	private String phone;
	private String id;
	private String user;
	// Get and Set methods
	public void setType(String type) { this.type = type; }
	public String getType() { return this.type; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }
	public void setID(String id) { this.id = id; }
	public String getID() { return this.id; }
	// Default constructor
	public CustomerPhone() {
		this.type = "";
		this.phone = "";
	}
	// Saves the phone
	public String save() throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		return db.createPhone(this);
	}
	
	public void update() throws UnknownHostException {
		PhoneDAO db = PhoneDAO.getInstance();
		boolean stat = db.updateCustomerPhoneById(this);
		System.out.println(stat);
	}
	// Custom constructor
	public CustomerPhone(String id) {
		PhoneDAO db = new PhoneDAO();
		DBObject object = db.findPhoneById(id);
		this.type = (String)object.get("type");
		this.phone = (String)object.get("phone");
		this.id = id;
		this.user = (String)object.get("user");
	}
	
	
}
