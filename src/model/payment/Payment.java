package model.payment;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.DBObject;

import dal.payment.PaymentDAO;
import model.billing.Billing;
import model.order.OrderItem;

public class Payment {
public enum PaymentType { CC, PAYPAL, BITCOIN, NONE }
	//attrs
	private String billing;
	private Billing billingobject;
	public PaymentType type;
	private String id;
	//CC attrs
	private String CCnum = "";
	private String CCexp = "";
	private String CCsec = "";
	public void setCCnum(String ccnum) { this.CCnum = ccnum; }
	public void setCCexp(String ccexp) { this.CCexp = ccexp; }
	public void setCCsec(String ccsec) { this.CCsec = ccsec; }
	public String getCCnum() { return this.CCnum; }
	public String getCCexp() { return this.CCexp; }
	public String getCCsec() { return this.CCsec; }
	//PP attrs
	private String PPacc = "";
	public void setPPacc(String ppacc) { this.PPacc = ppacc; }
	public String getPPacc() { return this.PPacc; }
	//BT attrs
	private String BTacc = "";
	public void setBTacc(String btacc) { this.BTacc = btacc; }
	public String getBTacc() { return this.BTacc; }
	
	//Sets and Gets payment
	public void setPaymentType(PaymentType typ) { this.type = typ; }
	public PaymentType getPaymentType() { return this.type; }
	//Sets and Gets billing
	public void setBilling(String nam) { this.billing = nam; }
	public String getBilling() { return this.billing; }
	public Billing getBillingObject() { return this.billingobject; }
	//Do the fund
	public void doRefund(int cost) {
		System.out.println("Order Refunded.");
	}
	public Payment() {
		
	}
	
	public PaymentType type(int x) {
		switch(x) {
		case 1:
			return PaymentType.CC;
		case 2:
			return PaymentType.PAYPAL;
		case 3:
			return PaymentType.BITCOIN;
		case 0:
			return PaymentType.NONE;
		}
		return PaymentType.CC;
	}
	
	public void update() throws UnknownHostException {
		PaymentDAO db = PaymentDAO.getInstance();
		db.updatePaymentById(this, this.id);
	}
	
	public Payment(String id) throws UnknownHostException {
		if (!id.equals("")) {
			PaymentDAO db = PaymentDAO.getInstance();
			DBObject object = db.findPaymentById(id);
			System.out.println(object);
			
			this.billing = (String)object.get("billing");
			if (!this.billing.equals("")) {
				//this.billingobject = new Billing(this.billing);
			}
			int code = (int)object.get("paytype");
			this.type = this.type(code);
			//RebuildObject
			this.CCnum = (String)object.get("ccnum");
			this.CCexp = (String)object.get("ccexp");
			this.CCsec = (String)object.get("ccsec");
			this.PPacc = (String)object.get("ppacc");
			this.BTacc = (String)object.get("btacc");
			this.id = id;
		} else {
			this.type = PaymentType.NONE;
		}
	}
	// Saves the payment 
	public String save() throws UnknownHostException {
		PaymentDAO db = PaymentDAO.getInstance();
		return db.createPayment(this);
	}
	//Make Payment
	public boolean makepayment(ArrayList<OrderItem> data) throws UnknownHostException {
		System.out.println("<<<<Payment Started>>>>");
		for (OrderItem o: data) {
			int amount = (o.getProduct().getCost() * o.getQuantity());
			System.out.println("Product: " + o.getProduct().getName() + " Cost: " + amount + " Pay Account: " + o.getProduct().getPartner().getCompany());
			System.out.println("Create Report ID:" + o.getProduct().createReport(o.getQuantity()));
		}
		System.out.println("<<<<Payment Ended>>>>");
		return true;
	}
}
