package com.ws.project.partner;

import java.util.ArrayList;

import com.ws.project.order.Order;
import com.ws.project.order.OrderItem;

public class PartnerOrder {
	//attrs
	private Order order;
	private ArrayList<OrderItem> ordereditems;
	//Creates the partner order
	public PartnerOrder(Order o,ArrayList<OrderItem> oit) {
		this.order = o;
		this.ordereditems = oit;
	}
	//Gets orders and items
	public Order getOrder() { return order; }
	public ArrayList<OrderItem> getOrderedItems() { return ordereditems; }
}
