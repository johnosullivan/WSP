package model.order;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import dal.order.OrderDAO;
import model.customer.Customer;
import model.helper.Helper;

public class Order {
	//Different order types
	public enum OrderStatusType { PROCESSING, PROCESSED, SHIPPED, INROUTE, DELIVERED, CANCELED, PAYMENTFAILED }
	//attrs
	private ArrayList<OrderItem> product;
	private Customer buyer;
	private OrderStatusType orderstatus;
	private String shippingaddress;
	private ShippingAddress shippingobject;
	private String comfirmnumber;
	private String id;
	//Sets and Get shipping address
	public void setShipping(String stat) { this.shippingaddress = stat; }
	public String getShipping() { return this.shippingaddress; }
	public ShippingAddress getShippingObject() { return this.shippingobject; }
	
	//Sets and Gets the order status
	public void setOrderStatus(OrderStatusType stat) { this.orderstatus = stat; }
	public OrderStatusType getOrderStatus() { return this.orderstatus; }
	//Sets and Gets the product list
	public void setProducts(ArrayList<OrderItem> pro) { this.product = pro; }
	public ArrayList<OrderItem> getProducts() { return this.product; }
	
	//Sets and Gets the buyer
	public void setBuyer(Customer us) { this.buyer = us; }
	public Customer getBuyer() { return this.buyer; }
	//Gets the confirm code
	public String getConfirmNumber() { return this.comfirmnumber; }
	//Sets the product as shipped
	public boolean productsShipped() throws UnknownHostException {
		this.orderstatus = OrderStatusType.SHIPPED;
		OrderDAO db = OrderDAO.getInstance();
		db.updateOrder(this);
		return true;
	}
	//Helper class 
	public String orderStatusString() {
			switch(this.orderstatus) {
			case PROCESSING:	return "PROCESSING";
			case PROCESSED:		return "PROCESSED";
			case SHIPPED:		return "SHIPPED";
			case INROUTE:		return "INROUTE";
			case DELIVERED:		return "DELIVERED";
			case CANCELED:		return "CANCELED";
			case PAYMENTFAILED:	return "PAYMENTFAILED";
			default:
				break;
			}
			return "";
		}
	//Checks if a product is in the order
	public boolean isProductInOrder(String proid) {
		for (OrderItem or: this.product) {
			if (or.getProduct().getID().equals(proid)) {
				return true;
			}
		}
		return false;
	}
	//Sets the order and products as delivered
	public boolean productsDelivered() throws UnknownHostException {
		this.orderstatus = OrderStatusType.DELIVERED;
		OrderDAO db = OrderDAO.getInstance();
		db.updateOrder(this);
		return true;
	}
	//Processes the order
	public String process() throws UnknownHostException {
		this.orderstatus = OrderStatusType.PROCESSING;
		OrderDAO db = OrderDAO.getInstance();
		//if (this.buyer.getPayment().makepayment(this.product)) {
		this.orderstatus = OrderStatusType.PROCESSED;
		this.comfirmnumber = "754754667396675466739739";
		return db.createOrder(this);
		/*} else {
			this.orderstatus = OrderStatusType.PAYMENTFAILED;
		}
		return "";*/
	}
	public Order() { }
	//Gets all product managed by partner id
	public ArrayList<OrderItem> getProductForPartner(String partnerid) {
		ArrayList<OrderItem> data = new ArrayList<OrderItem>();
		for (OrderItem o: this.product) {
			if (o.isProductOfPartner(partnerid)) {
				data.add(o);
			}
		}
		return data;
	}
	//Cancels the order and refund the customer
	public boolean cancelOrder() throws UnknownHostException {
		OrderDAO db = OrderDAO.getInstance();
		if (Helper.statuscode(this.orderstatus) >= 3) { return false; }
		System.out.println("Order#:" + this.id + " Refund: " + this.total());
		this.orderstatus = OrderStatusType.CANCELED;
		for (OrderItem o: this.product) { o.cancelled(); }
		return db.cancelledOrder(this);
	}
	//Helper function for the order status
	public OrderStatusType getStatusTypeFInt(int x) {
		if (x == 1) {
			return OrderStatusType.PROCESSING;
		}
		if (x == 2) {
			return OrderStatusType.PROCESSED;
		}
		if (x == 3) {
			return OrderStatusType.SHIPPED;
		}
		if (x == 4) {
			return OrderStatusType.INROUTE;
		}
		if (x == 5) {
			return OrderStatusType.DELIVERED;
		}
		if (x == 6) {
			return OrderStatusType.CANCELED;
		}
		if (x == 7) {
			return OrderStatusType.PAYMENTFAILED;
		}
		
		return OrderStatusType.PROCESSING;
	}
	//Creates a order object from a database document with id	
	public Order(String ordernumber) throws UnknownHostException {
		OrderDAO db = OrderDAO.getInstance();
		DBObject object = db.findOrderById(ordernumber);
		this.comfirmnumber = (String)object.get("comfirmnumber");
		this.buyer = new Customer((String)object.get("customer"));
		this.shippingaddress = (String)object.get("shipping");
		this.shippingobject = new ShippingAddress(this.shippingaddress);
		this.orderstatus = getStatusTypeFInt((int)object.get("status"));
		this.id = "" + object.get("_id");
		this.product = new ArrayList<OrderItem>();
		BasicDBList product = (BasicDBList)object.get("products");
		for(Object el: product) {
			String[] data = ((String)el).split("_");
			OrderItem o = new OrderItem(data[0],Integer.parseInt(data[1]));
			this.product.add(o);
		}
	}
	//Gets the order id
	public String getID() {
		return this.id;
	}
	//Gets the total of the order
	public int total() {
		int total = 0;
		Iterator<OrderItem> products = product.iterator();
		while (products.hasNext()) {
			OrderItem item = products.next();
			total = total + (item.getProduct().getCost() * item.getQuantity());
		}
		return total;
	}
}
