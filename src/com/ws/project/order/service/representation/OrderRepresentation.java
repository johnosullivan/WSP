package com.ws.project.order.service.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {

	private ArrayList<OrderItemRepresentation> items;
	private String address;
	private String customer;
	private String comfirm;
	private String status;
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getComfirm() {
		return this.comfirm;
	}
	
	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<OrderItemRepresentation> getItems() {
		return this.items;
	}
	
	public void setItems(ArrayList<OrderItemRepresentation> results) {
		this.items = results;
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
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
	

}
