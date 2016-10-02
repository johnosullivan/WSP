package com.ws.project;

import com.mongodb.DBObject;

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
	public String create() {
		Database db = Database.getInstance();
		id = db.createProduct(this);
		return id;
	}
	//Sold the product
	public boolean sold(int am) {
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
	public boolean update() {
		Database db = Database.getInstance();
		db.updateProductById(this);
		return true;
	}
	//Gets the id
	public String getID() { return this.id; }
	public Product() { }
	//Prints the detail of product
	public void PrintDetail() {
		System.out.println(this.name + " " + this.description + " " + this.cost);
		Partner thisproductpartner = new Partner(this.partnerid);
		thisproductpartner.PrintDetail();
	}
	//Restock the product
	public void restock(int am) {
		this.invein = this.invein + am;
		if (this.invein == 0) {
			this.available = false;
		} else {
			this.available = true;
		}
		Database db = Database.getInstance();
		db.updateProductById(this);
	}
	public Partner getPartner() {
		Partner p = new Partner(this.partnerid);
		return p;
	}
	//Creates the product from the database with id
	public Product(String id) {
		Database db = Database.getInstance();
		DBObject object = db.findProductsById(id);
		this.name = (String)object.get("name");
		this.description = (String)object.get("des");
		this.cost = (int)object.get("cost");
		this.invein = (int)object.get("inventory");
		this.curcode = (String)object.get("curcode");
		this.partnerid = (String)object.get("partnerid");
		this.id = id;
		if (this.invein == 0) {
			this.available = false;
		} else {
			this.available = true;
		}
	}	
}
