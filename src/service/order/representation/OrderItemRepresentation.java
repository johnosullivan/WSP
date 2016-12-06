package service.order.representation;

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
	private String productname;
	
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public int getQ() {
		return this.q;
	}

	public void setQ(int q) {
		this.q = q;
	}
	
	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}
	
}
