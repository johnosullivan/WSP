package com.ws.project.billing;

import com.ws.project.address.Address;

public class Billing {

	private Address address;

	// Sets and Gets Address
	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

	public Billing() {

	}

	public Billing(String id) {
		this.address = new Address(id);
	}

}
