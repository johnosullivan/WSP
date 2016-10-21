package com.ws.project.payment;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.DBObject;
import com.ws.project.billing.Billing;
//import com.ws.project.dao.Database;
import com.ws.project.dao.PaymentDAO;
import com.ws.project.order.OrderItem;

public class Payment {
public enum PaymentType { CC, PAYPAL, BITCOIN }
	//attrs
	private String billing;
	private Billing billingobject;
	public PaymentType type;
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
		}
		return PaymentType.CC;
	}
	public Payment(String id) throws UnknownHostException {
		if (!id.equals("")) {
			PaymentDAO db = PaymentDAO.getInstance();
			DBObject object = db.findPaymentById(id);
			this.billing = (String)object.get("billing");
			if (!this.billing.equals("")) {
				this.billingobject = new Billing(this.billing);
			}
			int code = (int)object.get("paytype");
			this.type = this.type(code);
		} else {
			
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
