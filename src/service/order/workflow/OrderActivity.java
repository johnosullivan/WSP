package service.order.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import model.customer.Customer;
import model.order.Order;
import model.order.OrderItem;
import model.partner.Partner;
import model.partner.PartnerOrder;
import model.product.Product;
import service.order.representation.OrderCustomerRepresentation;
import service.order.representation.OrderItemRepresentation;
import service.order.representation.OrderPartnerObject;
import service.order.representation.OrderPartnerRepresentation;
import service.order.representation.OrderPartnerStatusRequest;
import service.order.representation.OrderRepresentation;
import service.order.representation.OrderRequest;
import service.order.representation.OrderStatusRepresentation;


public class OrderActivity {

	public OrderRepresentation postOrder(OrderRequest request) throws UnknownHostException {
		
		ArrayList<OrderItemRepresentation> items = request.getItems();
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();

		for (OrderItemRepresentation i : items) {
			OrderItem temp = new OrderItem();
			temp.setProduct(new Product(i.getProductid()));
			temp.setQuantity(i.getQ());
			carts.add(temp);
		}
		Order orderone = new Order();
		Customer cus = new Customer(request.getCustomer());
		orderone.setBuyer(cus);
		orderone.setProducts(carts);
		orderone.setShipping(request.getAddress());
		//System.out.println("Pre Order Ran!");
		String orderonetest = orderone.process();
		//System.out.println("Post Order Ran!");
		
		OrderRepresentation order = new OrderRepresentation();
		order.setAddress(request.getAddress());
		order.setCustomer(request.getCustomer());
		order.setItems(items);
		order.setId(orderonetest);
		order.setComfirm(orderone.getConfirmNumber());
		order.setStatus(orderone.orderStatusString());
		return order;
	}
	
	public boolean updateStatus(OrderPartnerStatusRequest req) throws UnknownHostException {
		Order order = new Order(req.getOrderid());
		if (req.getCode() == 1) {
			order.productsShipped();
		}
		return true;
	}
	
	public OrderCustomerRepresentation getOrdersCustomer(String id) throws UnknownHostException {
		Customer cus = new Customer(id);
		OrderCustomerRepresentation o = new OrderCustomerRepresentation();
		ArrayList<OrderRepresentation> array = new ArrayList<OrderRepresentation>();
		for (Order order : cus.getMyOrder()) {
			OrderRepresentation ord = new OrderRepresentation();
			ord.setAddress(order.getShippingObject().full());
			ord.setComfirm(order.getConfirmNumber());
			ord.setId(id);
			ord.setCustomer(order.getBuyer().fullName());
			ArrayList<OrderItemRepresentation> products = new ArrayList<OrderItemRepresentation>();
			
			for (OrderItem i : order.getProducts()) {
				OrderItemRepresentation temp = new OrderItemRepresentation();
				temp.setProductid(i.getProduct().getID());
				temp.setQ(i.getQuantity());
				products.add(temp);
			}
			ord.setTotal(order.total());
			ord.setItems(products);
			ord.setStatus(order.orderStatusString());
			array.add(ord);
		}
		o.setOrders(array);
		
		return o;
	}
	
	public OrderPartnerRepresentation getOrdersPartner(String id) throws UnknownHostException {
		Partner part = new Partner(id);
		OrderPartnerRepresentation ret = new OrderPartnerRepresentation();
		ArrayList<OrderPartnerObject> orders = new ArrayList<OrderPartnerObject>();
		
		for (PartnerOrder order : part.getOrders()) {
			OrderPartnerObject temp = new OrderPartnerObject();
			temp.setOrderId(order.getOrder().getID());
			String addressid = order.getOrder().getShippingObject().full();
			temp.setShipping(addressid);
			ArrayList<OrderItemRepresentation> products = new ArrayList<OrderItemRepresentation>();
			temp.setOrderStatus(order.getOrder().orderStatusString());
			for (OrderItem i : order.getOrderedItems()) {
				OrderItemRepresentation tempw = new OrderItemRepresentation();
				tempw.setProductid(i.getProduct().getID());
				tempw.setQ(i.getQuantity());
				products.add(tempw);
			}
			temp.setItems(products);
			orders.add(temp);
		}
		ret.setOrders(orders);
		return ret;
	}
	
	public OrderStatusRepresentation getOrderStatus(String id) throws UnknownHostException {
		OrderStatusRepresentation status = new OrderStatusRepresentation();
		Order order = new Order(id);
		status.setStatus(order.orderStatusString());
		return status;
	}
	
	public OrderRepresentation getOrder(String id) throws UnknownHostException {
		Order order = new Order(id);
		OrderRepresentation ord = new OrderRepresentation();
		ord.setAddress(order.getShipping());
		ord.setComfirm(order.getConfirmNumber());
		ord.setId(id);
		ord.setCustomer(order.getBuyer().fullName());
		ArrayList<OrderItemRepresentation> products = new ArrayList<OrderItemRepresentation>();
		
		for (OrderItem i : order.getProducts()) {
			OrderItemRepresentation temp = new OrderItemRepresentation();
			temp.setProductid(i.getProduct().getID());
			temp.setQ(i.getQuantity());
			products.add(temp);
		}
		ord.setTotal(order.total());
		ord.setItems(products);
		ord.setStatus(order.orderStatusString());
		return ord;
	}
	
	public OrderRepresentation cancelOrder(String id) throws UnknownHostException {
		Order order = new Order(id);
		boolean isCancel = order.cancelOrder();
		if (isCancel) {
			OrderRepresentation ord = new OrderRepresentation();
			ord.setAddress(order.getShipping());
			ord.setComfirm(order.getConfirmNumber());
			ord.setId(id);
			ord.setCustomer(order.getBuyer().fullName());
			ArrayList<OrderItemRepresentation> products = new ArrayList<OrderItemRepresentation>();
			
			for (OrderItem i : order.getProducts()) {
				OrderItemRepresentation temp = new OrderItemRepresentation();
				temp.setProductid(i.getProduct().getID());
				temp.setQ(i.getQuantity());
				products.add(temp);
			}
			ord.setTotal(order.total());
			ord.setItems(products);
			ord.setStatus(order.orderStatusString());
			return ord;
		} else {
			return new OrderRepresentation();
		}
		
	}
	
	
	
}
