package service.payment.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PaymentPPRepresentation implements PaymentRepresentationInterface {


	private String billing;
	//
	public void setBilling(String billing) { 	this.billing = billing; }
	public String getBilling() { return this.billing; }
	
	//PP attrs
	private String ppacc = "";
	public void setppacc(String ppacc) { this.ppacc = ppacc; }
	public String getppacc() { return this.ppacc; }


	
}
