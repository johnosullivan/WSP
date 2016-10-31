package model.billing;

public class Billing {

	private BillingAddress address;

	// Sets and Gets Address
	public void setAddress(BillingAddress address) {
		this.address = address;
	}
	public BillingAddress getAddress() {
		return this.address;
	}

	public Billing() {}
	public Billing(String id) {
		if (!id.equals("")) {
			this.address = new BillingAddress(id);
		}
	}
}
