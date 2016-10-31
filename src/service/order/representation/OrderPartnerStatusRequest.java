package service.order.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "statusorder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderPartnerStatusRequest {

	private String orderid;
	private int code;
	
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
}
