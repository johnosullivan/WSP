package com.ws.project.order.service.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.ws.project.order.service.representation.OrderItemRepresentation;
import com.ws.project.order.service.representation.OrderRepresentation;
import com.ws.project.order.service.representation.OrderRequest;


public class OrderActivity {

	public OrderRepresentation postOrder(OrderRequest request) throws UnknownHostException {
		
		ArrayList<OrderItemRepresentation> items = request.getItems();
		
		for (OrderItemRepresentation i : items) {
			System.out.println("ProductID: " + i.getProductId() + " Q: " + i.getQ());
		}
		
		System.out.println("Address: " + request.getAddress());
		System.out.println("Customer: " + request.getCustomer());

		OrderRepresentation order = new OrderRepresentation();
		order.setAddress(request.getAddress());
		order.setCustomer(request.getCustomer());
		order.setItems(items);
		return order;
	}
	
	
}
