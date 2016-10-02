package com.ws.project;

import java.util.ArrayList;

import com.mongodb.DBObject;

public class Payment {
public enum PaymentType { CC, PAYPAL, BITCOIN }
	//attrs
	private String billingaddress;
	public PaymentType type;
	//Sets and Gets payment
	public void setPaymentType(PaymentType typ) { this.type = typ; }
	public PaymentType getPaymentType() { return this.type; }
	//Sets and Gets billing
	public void setBilling(String nam) { this.billingaddress = nam; }
	public String getBilling() { return this.billingaddress; }
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
	public Payment(String id) {
		if (!id.equals("")) {
			Database db = Database.getInstance();
			DBObject object = db.findPaymentById(id);
			this.billingaddress = (String)object.get("billing");
			int code = (int)object.get("paytype");
			this.type = this.type(code);
		} else {
			
		}
	}
	//Make 
	public boolean makepayment(ArrayList<OrderItem> data) {
		System.out.println("<<<<Payment Started>>>>");
		for (OrderItem o: data) {
			int amount = (o.getProduct().getCost() * o.getQuantity());
			System.out.println("Product: " + o.getProduct().getName() + " Cost: " + amount + " Pay Account: " + o.getProduct().getPartner().getCompany());
		}
		System.out.println("<<<<Payment Ended>>>>");
		return true;
	}
}
