package com.ws.project.billing;

public class Billing {

	private BillingAddress address;

	// Sets and Gets Address
	public void setAddress(BillingAddress address) {
		this.address = address;
	}

	public BillingAddress getAddress() {
		return this.address;
	}

	public Billing() {

	}

	public Billing(String id) {
		if (!id.equals("")) {
			System.out.println("Building Billing Address");
			this.address = new BillingAddress(id);
		}
	}

}
