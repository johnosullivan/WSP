package service.order.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderCustomerRepresentation {

	
	private ArrayList<OrderRepresentation> orders;
	
	public ArrayList<OrderRepresentation> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<OrderRepresentation> orders) {
		this.orders = orders;
	}
	
	
}
