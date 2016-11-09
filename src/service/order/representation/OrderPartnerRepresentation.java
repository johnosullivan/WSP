package service.order.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderPartnerRepresentation {

	private ArrayList<OrderPartnerObject> orders;
	
	public ArrayList<OrderPartnerObject> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderPartnerObject> orders) {
		this.orders = orders;
	}
	
	
}
