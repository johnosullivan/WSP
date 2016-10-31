package service.payment.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PaymentCCRepresentation implements PaymentRepresentationInterface {

	private String billing;
	//
	public void setBilling(String billing) { 	this.billing = billing; }
	public String getBilling() { return this.billing; }
	
	//
	private String ccnum = "";
	private String ccexp = "";
	private String ccsec = "";
	public void setccnum(String ccnum) { this.ccnum = ccnum; }
	public void setccexp(String ccexp) { this.ccexp = ccexp; }
	public void setccsec(String ccsec) { this.ccsec = ccsec; }
	public String getccnum() { return this.ccnum; }
	public String getccexp() { return this.ccexp; }
	public String getccsec() { return this.ccsec; }
	
	
}
