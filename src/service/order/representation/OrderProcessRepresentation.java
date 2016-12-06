package service.order.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "process")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderProcessRepresentation {

	
	private String order;
	
	public String getOrder() {	return this.order;	}
	
	public void setOrder(String order) {  this.order = order;  }
	
	private int action;
	
	public int getAction() {	return this.action;	}
	
	public void setAction(int action) {  this.action = action;  }
	
	
}
