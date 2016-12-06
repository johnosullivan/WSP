package service.order.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import service.AbstractRepresentation;


@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderPartnerObject extends AbstractRepresentation {

	private String shipping;
	private String orderid;
	private String orderstatus;
	private String comfirm;
	private ArrayList<OrderItemRepresentation> items;
	
	public ArrayList<OrderItemRepresentation> getItems() {
		return this.items;
	}
	
	public void setItems(ArrayList<OrderItemRepresentation> results) {
		this.items = results;
	}
	
	public String getShipping() {
		return this.shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	
	public String getComfir() {
		return this.comfirm;
	}

	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}
	
	public String getOrderStatus() {
		return this.orderstatus;
	}

	public void setOrderStatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	public String getOrderId() {
		return this.orderid;
	}

	public void setOrderId(String orderid) {
		this.orderid = orderid;
	}
	
}