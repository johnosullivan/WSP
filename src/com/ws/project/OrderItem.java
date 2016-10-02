package com.ws.project;


public class OrderItem {
	//attrs
	private Product product;
	private int quantity;
	//Gets and Sets the product
	public void setProduct(Product pro) { this.product = pro; }
	public Product getProduct() { return this.product; }
	//Gets and sets the quantity
	public void setQuantity(int cos) { this.quantity = cos; }
	public int getQuantity() { return this.quantity; }
	public OrderItem() { }
	//Restock the order
	public void cancelled() {
		this.product.restock(this.quantity);
	}
	//Check if product is from partner
	public boolean isProductOfPartner(String id) {
		//System.out.println("Partner: " + id);
		if (this.product.getSeller().equals(id)) {
			return true;
		}
		return false;
	}
	//Creates a orderitem with paras
	public OrderItem (String productid, int amount) {
		this.quantity = amount;
		this.product = new Product(productid);
		
	}
}
