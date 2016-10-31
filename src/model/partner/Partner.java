package model.partner;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

//import java.util.ArrayList;

import com.mongodb.DBObject;

import dal.address.AddressDAO;
import dal.partner.PartnerDAO;
import dal.phone.PhoneDAO;
import model.order.Order;
import model.order.OrderItem;
import model.payment.Payment;

public class Partner {
	//attrs
	private String account;
	private String first;
	private Payment payment;
	private String middle;
	private String last;
	private String id;
	private String payid;
	private String company;
	private String email;
	private String propicURL;
	//private String phone;
	private String homepage;
	//All phone and address
	private ArrayList<PartnerAddress> alladdress;
	private ArrayList<PartnerPhone> allphone;
	//Sets and Gets Payment
	public void setPayment(String payid) { 
		
		this.payid = payid; 
	}
	public Payment getPayment() { return this.payment; }
	//Sets the account
	public void setAccount(String acc) { this.account = acc; }
	public String getAccount() { return this.account; }
	//Sets the account
	public void setCompany(String com) { this.company = com; }
	public String getCompany() { return this.company; }
	//Creates the partner
	public String create() throws UnknownHostException {
		PartnerDAO db = PartnerDAO.getInstance();
		this.id = db.createPartner(this);
		return this.id;
	}
	//Updates the partner object
	public boolean update()  throws UnknownHostException{
		PartnerDAO db = PartnerDAO.getInstance();
		db.updatePartnerById(this);
		return true;
	}
	public ArrayList<PartnerAddress> getAllAddress() throws UnknownHostException {
		return this.alladdress;
	}
	
	public ArrayList<PartnerPhone> getAllPhone() throws UnknownHostException {
		return this.allphone;
	}
	//Prints the details
	public void PrintDetail() {
		System.out.println(this.first + " " + this.middle + " " + this.last);
	}
	//Gets the orders of this partner
	public ArrayList<PartnerOrder> getOrders() throws UnknownHostException {
		PartnerDAO db = PartnerDAO.getInstance();
		ArrayList<Order> orders = db.getPartnerOrders(this);
		Iterator<Order> itorder = orders.iterator();
		ArrayList<PartnerOrder> finalorder = new ArrayList<PartnerOrder>();
		while(itorder.hasNext()) {
			Order temp = itorder.next();
			ArrayList<OrderItem> myorderedproducts = temp.getProductForPartner(this.id);
			//System.out.println(myorderedproducts.size());
			PartnerOrder ptemp = new PartnerOrder(temp,myorderedproducts);
			finalorder.add(ptemp);
		}
		return finalorder;
	}
	public Partner() { 
		this.first = "";
		this.middle = "";
		this.last = "";
		this.payid = "";
		this.company = "";
		this.email = "";
		//this.phone = "";
		this.propicURL = "";
		this.homepage = "";
	}
	public String getPId() {
		return this.payid;
	}
	//Creates partner from the database with id
	public Partner(String id) throws UnknownHostException {
		PartnerDAO db = PartnerDAO.getInstance();
		DBObject object = db.findPartnersById(id);
		this.first = (String)object.get("first");
		this.middle = (String)object.get("middle");
		this.last = (String)object.get("last");
		this.id = id;
		this.payid = (String)object.get("payid");
		this.company = (String)object.get("company");
		this.email = (String)object.get("email");
		//this.phone = (String)object.get("phone");
		this.propicURL = (String)object.get("propicURL");
		this.homepage = (String)object.get("homepage");
		this.payment = new Payment(this.payid);
		
		AddressDAO dbaddress = AddressDAO.getInstance();
		this.alladdress =  dbaddress.allAddressForPartner(this.id);
		
		PhoneDAO dbphone = PhoneDAO.getInstance();
		this.allphone = dbphone.allPhoneForPartner(this.id);
	}
	//Sets and Gets Email
	public void setEmail(String stat) { this.email = stat; }
	public String getEmail() { return this.email; }
	//Sets and Gets Propic
	public void setPP(String stat) { this.propicURL = stat; }
	public String getPP() { return this.propicURL; }
	//Sets and Gets Phone
	//public void setPhone(String stat) { this.phone = stat; }
	//public String getPhone() { return this.phone; }
	//Sets and Gets Phone
	public void setHomepage(String stat) { this.homepage = stat; }
	public String getHomepage() { return this.homepage; }
	
	public String fullName() {
		return this.first + " " + this.middle + " " + this.last;
	}
	//Gets the id 
	public String getID() { return this.id; }
	//Gets and sets the first name
	public void setFirst(String fir) { this.first = fir; }
	public String getFirst() { return this.first; }
	//Gets and sets the middle name
	public void setMiddle(String mid) { this.middle = mid; }
	public String getMiddle() { return this.middle; }
	//Gets and sets the last name.
	public void setLast(String las) { this.last = las; }
	public String getLast() { return this.last; }
}
