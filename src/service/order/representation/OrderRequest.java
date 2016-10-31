package service.order.representation;

import java.util.ArrayList;

public class OrderRequest {

	private ArrayList<OrderItemRepresentation> items;
	private String address;
	private String customer;
	
	public ArrayList<OrderItemRepresentation> getItems() {
		return this.items;
	}
	
	public void setItems(ArrayList<OrderItemRepresentation> items) {
		this.items = items;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCustomer() {
		return this.customer;
	}
	
	public void setCustomers(String customer) {
		this.customer = customer;
	}
	
}
