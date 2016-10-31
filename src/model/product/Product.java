package model.product;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.DBObject;

import dal.product.ProductDAO;
import model.partner.Partner;
import model.report.Report;

public class Product {
	//attrs
	public enum ProductType { BOOK, CABLE, FOOD, DRINK, TOOLS, PARTS, COMPUTER, FURNITURE, APPLIANCE, CLOTHING, INDOOR, OUTDOOR, SUPPLIES, SPORTS, ACCESSORIES, ELECTRONICS};
	private String name;
	private String description;
	private int cost;
	private int invein;
	private String curcode;
	private String partnerid;
	public ProductType type;
	private String id;
	private boolean available;
	//Sets and gets product
	public void setProductType(ProductType typ) { this.type = typ; }
	public ProductType getProductType() { return this.type; }
	//Sets and gets name 
	public void setName(String nam) { this.name = nam; }
	public String getName() { return this.name; }
	//Gets if available
	public boolean getAvailable() { return this.available; }
	//Gets and sets Description
	public void setDescription(String des) { this.description = des; }
	public String getDescription() { return this.description; }
	//Gets and sets cost
	public void setCost(int cos) { this.cost = cos; }
	public int getCost() { return this.cost; }
	//Gets and sets inventory
	public void setInventory(int cos) { this.invein = cos; }
	public int getInventory() { return this.invein; }
	//Gets and sets cost code
	public void setCostCode(String curc) { this.curcode = curc; }
	public String getCostCode() { return this.curcode; }
	//Gets and sets seller
	public void setSeller(String sell) { this.partnerid = sell; }
	public String getSeller() { return this.partnerid; }
	//Creates the product in the database
	public String create() throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		id = db.createProduct(this);
		return id;
	}
	//Sold the product
	public boolean sold(int am) throws UnknownHostException {
		if (this.invein - am < 0) {
			return false;
		}
		this.invein = this.invein - am;
		
		if (this.invein == 0) {
			this.available = false;
		} else {
			this.available = true;
		}
		
		return update();
	}
	//Updates the product info
	public boolean update() throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		db.updateProductById(this); 
		return true;
	}
	//Gets the id
	public String getID() { return this.id; }
	public void setID(String s) { this.id = s; }
	public Product() { }
	//Prints the detail of product
	public void PrintDetail() throws UnknownHostException {
		System.out.println(this.name + " " + this.description + " " + this.cost);
		Partner thisproductpartner = new Partner(this.partnerid);
		thisproductpartner.PrintDetail();
	}
	//Restock the product
	public void restock(int am) throws UnknownHostException {
		this.invein = this.invein + am;
		if (this.invein == 0) {
			this.available = false;
		} else {
			this.available = true;
		}
		ProductDAO db = ProductDAO.getInstance();
		db.updateProductById(this);
	}
	public Partner getPartner() throws UnknownHostException {
		Partner p = new Partner(this.partnerid);
		return p;
	}
	//Gets the report for this product
	public Report getReport() throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		return db.createReportForProduct(this);
	}
	public String createReport(int amount) throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		Date dt = new Date();
		return db.createNewReport(this, dt, amount);
	}
	//Creates the product from the database with id
	public Product(String id) throws UnknownHostException {
		ProductDAO db = ProductDAO.getInstance();
		DBObject object = db.findProductsById(id);
		this.name = (String)object.get("name");
		this.description = (String)object.get("des");
		this.cost = (int)object.get("cost");
		this.invein = (int)object.get("inventory");
		this.curcode = (String)object.get("costcode");
		this.partnerid = (String)object.get("partnerid");
		this.id = id;
		if (this.invein == 0) {
			this.available = false;
		} else {
			this.available = true;
		}
	}	
}
