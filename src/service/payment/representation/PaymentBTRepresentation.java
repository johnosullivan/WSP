package service.payment.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PaymentBTRepresentation implements PaymentRepresentationInterface {


	private String billing;
	//
	public void setBilling(String billing) { 	this.billing = billing; }
	public String getBilling() { return this.billing; }

	//BT attrs
	private String btacc = "";
	public void setbtacc(String btacc) { this.btacc = btacc; }
	public String getbtacc() { return this.btacc; }
	
}
