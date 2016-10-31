package service.payment.representation;

public class PaymentRequest {

	private String user;
	private String type;
	private String billing;
	//
	public void setBilling(String billing) { 	this.billing = billing; }
	public String getBilling() { return this.billing; }
	
	public void setType(String type) { 	this.type = type; }
	public String getType() { return this.type; }
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }
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
	//PP attrs
	private String ppacc = "";
	public void setppacc(String ppacc) { this.ppacc = ppacc; }
	public String getppacc() { return this.ppacc; }
	//BT attrs
	private String btacc = "";
	public void setbtacc(String btacc) { this.btacc = btacc; }
	public String getbtacc() { return this.btacc; }
	
}
