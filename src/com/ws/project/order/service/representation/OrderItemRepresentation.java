package com.ws.project.order.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "orderitem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderItemRepresentation {

	private int q;
	private String productid;
	
	public int getQ() {
		return this.q;
	}

	public void setQ(int q) {
		this.q = q;
	}
	
	public String getProductId() {
		return this.productid;
	}

	public void setProductId(String productid) {
		this.productid = productid;
	}
	
	
}
