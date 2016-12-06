package service.order.workflow;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dal.order.OrderDAO;
import service.Link;
import model.customer.Customer;
import model.customer.CustomerAddress;
import model.helper.Helper;
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
import service.order.representation.OrderProcessRepresentation;
import service.order.representation.OrderRepresentation;
import service.order.representation.OrderRequest;
import service.order.representation.OrderStatusRepresentation;


public class OrderActivity {

	static public String orderservice = "orderservice/order";
	
	public OrderRepresentation postOrder(OrderRequest request,UriInfo uri) throws UnknownHostException, JSONException {
		
		ArrayList<OrderItemRepresentation> items = request.getItems();
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();

		for (OrderItemRepresentation i : items) {
			OrderItem temp = new OrderItem();
			Product pro = new Product(i.getProductid());
			i.setProductname(pro.getName());
			temp.setProduct(pro);
			temp.setQuantity(i.getQ());
			carts.add(temp);
		}
		Order orderone = new Order();
		Customer cus = new Customer(request.getCustomer());
		orderone.setBuyer(cus);
		orderone.setProducts(carts);
		orderone.setShipping(request.getAddress());
		String orderonetest = orderone.create();		
		OrderRepresentation order = new OrderRepresentation();
		
		CustomerAddress cusad = new CustomerAddress(request.getAddress());
		Customer customer = new Customer(request.getCustomer());
		order.setAddress(cusad.full());
		order.setCustomer(customer.fullName());
		order.setItems(items);
		order.setId(orderonetest);
		order.setTotal(orderone.total());
		order.setComfirm(orderone.getConfirmNumber());
		order.setStatus(orderone.orderStatusString());
			
		System.out.println(orderonetest);

		
		 
		JSONObject obj = new JSONObject();
		obj.put("order", orderonetest);
		obj.put("action", 0);
		
		JSONObject objr = new JSONObject();
		objr.put("order", orderonetest);
		objr.put("action", 1);
		
			
			Link link = new Link("Process",uri.getBaseUri() + orderservice + "/status","PUT","application/json");
			link.setPayload(objr.toString());
			

			Link linkr = new Link("Return To Cart",uri.getBaseUri() + orderservice + "/status","PUT","application/json");
			linkr.setPayload(obj.toString());
			order.setLinks(link,linkr);
		
		
		
		
		
		return order;
	}
	
	public OrderRepresentation processOrder(OrderProcessRepresentation req,UriInfo uri) throws UnknownHostException {
		
		Order orderone = new Order(req.getOrder());
		
		OrderRepresentation order = new OrderRepresentation();

		if (req.getAction() == 0) {
			boolean status = orderone.restock();
			System.out.println(status);
			OrderDAO db = OrderDAO.getInstance();
			db.deleteOrderById(req.getOrder());
		}
		if (req.getAction() == 1) {
			boolean status = orderone.process();
			System.out.println(status);
			order.setAddress(orderone.getShippingObject().full());
			order.setCustomer(orderone.getBuyer().fullName());
			ArrayList<OrderItemRepresentation> products = new ArrayList<OrderItemRepresentation>();
			for (OrderItem i : orderone.getProducts()) {
				OrderItemRepresentation temp = new OrderItemRepresentation();
				temp.setProductid(i.getProduct().getID());
				temp.setProductname(i.getProduct().getName());
				temp.setQ(i.getQuantity());
				products.add(temp);
			}
			order.setItems(products);
			order.setId(req.getOrder());
			order.setComfirm(orderone.getConfirmNumber());
			order.setStatus(orderone.orderStatusString());		
			Link linkview = new Link("View",uri.getBaseUri() + orderservice + "/" + req.getOrder(),"GET","application/json");
			Link linkcancel = new Link("Cancel",uri.getBaseUri() + orderservice + "/" + req.getOrder(),"DELETE","application/json");
			order.setLinks(linkview,linkcancel);
		}
		
		return order;
	}
	
	public boolean updateStatus(OrderPartnerStatusRequest req) throws UnknownHostException {
		Order order = new Order(req.getOrderid());
		if (req.getCode() == 1) {
			order.productsShipped();
		}
		return true;
	}
	
	public boolean updateStatusCustomer(OrderPartnerStatusRequest req) throws UnknownHostException {
		Order order = new Order(req.getOrderid());
		if (req.getCode() == 1) {
			boolean status = order.productsDelivered();
			return status;
		}
		return true;
	}
	
	public OrderCustomerRepresentation getOrdersCustomer(String id,UriInfo uri) throws UnknownHostException, JSONException {
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
				temp.setProductname(i.getProduct().getName());
				temp.setQ(i.getQuantity());
				products.add(temp);
			}
			
			ord.setTotal(order.total());
			ord.setItems(products);
			ord.setStatus(order.orderStatusString());
			System.out.println(Helper.statuscode(order.getOrderStatus()));
			Link linkview = new Link("View",uri.getBaseUri() + orderservice + "/" + order.getID(),"GET","application/json");
			if (Helper.statuscode(order.getOrderStatus()) <= 2) {
				Link linkcancel = new Link("Cancel",uri.getBaseUri() + orderservice + "/" + order.getID(),"DELETE","application/json");
				ord.setLinks(linkview,linkcancel);
			} else {
				if (Helper.statuscode(order.getOrderStatus()) == 3) {
					JSONObject obj = new JSONObject();
					obj.put("orderid", order.getID());
					obj.put("code", 1);
					Link link = new Link("Delivered",uri.getBaseUri() + "orderservice/order/customer","PUT","application/json");
					link.setPayload(obj.toString());
					ord.setLinks(linkview,link);
				} else {
					if (Helper.statuscode(order.getOrderStatus()) == 5) {
						JSONObject obj = new JSONObject();
						obj.put("orderid", order.getID());
						obj.put("code", 1);
						Link link = new Link("Review",uri.getBaseUri() + "reviewservice/review","POST","application/json");
						link.setPayload(obj.toString());
						ord.setLinks(linkview,link);
					} else {
						ord.setLinks(linkview);
					}
				}
			}
						
			array.add(ord);
		}
		o.setOrders(array);
		
		return o;
	}
	
	public OrderPartnerRepresentation getOrdersPartner(String id,UriInfo uri) throws UnknownHostException, JSONException {
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
				tempw.setProductname(i.getProduct().getName());
				tempw.setQ(i.getQuantity());
				products.add(tempw);
			}
			
			
			
			if (Helper.statuscode(order.getOrder().getOrderStatus()) == 2) {
				JSONObject obj = new JSONObject();
				obj.put("orderid", order.getOrder().getID());
				obj.put("code", 1);
				
				Link link = new Link("Shipped Order",uri.getBaseUri() + "orderservice/order/partner","PUT","application/json");
				link.setPayload(obj.toString());
				temp.setLinks(link);
				
				
			} else {
			}
			temp.setComfirm(order.getOrder().getConfirmNumber());
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
	
	public OrderRepresentation getOrder(String id,UriInfo uri) throws UnknownHostException {
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
			temp.setProductname(i.getProduct().getName());
			temp.setQ(i.getQuantity());
			products.add(temp);
		}
		
		ord.setTotal(order.total());
		ord.setItems(products);
		ord.setStatus(order.orderStatusString());
		System.out.println(Helper.statuscode(order.getOrderStatus()));
		Link linkview = new Link("View",uri.getBaseUri() + orderservice + "/" + order.getID(),"GET","application/json");

		if (Helper.statuscode(order.getOrderStatus()) <= 2) {
			Link linkcancel = new Link("Cancel",uri.getBaseUri() + orderservice + "/" + order.getID(),"DELETE","application/json");
			ord.setLinks(linkview,linkcancel);
		} else {
			ord.setLinks(linkview);
		}
		
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
				temp.setProductname(i.getProduct().getName());
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
