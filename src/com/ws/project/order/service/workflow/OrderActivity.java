package com.ws.project.order.service.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.ws.project.customer.Customer;
import com.ws.project.order.Order;
import com.ws.project.order.OrderItem;
import com.ws.project.order.service.representation.OrderItemRepresentation;
import com.ws.project.order.service.representation.OrderRepresentation;
import com.ws.project.order.service.representation.OrderRequest;
import com.ws.project.product.Product;


public class OrderActivity {

	public OrderRepresentation postOrder(OrderRequest request) throws UnknownHostException {
		
		ArrayList<OrderItemRepresentation> items = request.getItems();
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();

		for (OrderItemRepresentation i : items) {
			OrderItem temp = new OrderItem();
			temp.setProduct(new Product(i.getProductId()));
			temp.setQuantity(i.getQ());
		}
		Order orderone = new Order();
		Customer cus = new Customer(request.getCustomer());
		orderone.setBuyer(cus);
		orderone.setProducts(carts);
		orderone.setShipping(request.getAddress());
		
		String orderonetest = orderone.process();
		
		
		OrderRepresentation order = new OrderRepresentation();
		order.setAddress(request.getAddress());
		order.setCustomer(request.getCustomer());
		order.setItems(items);
		order.setId(orderonetest);
		order.setComfirm(orderone.getConfirmNumber());
		order.setStatus(orderone.orderStatusString());
		return order;
	}
	
	
}
