package service.payment.workflow;

import java.net.UnknownHostException;

import dal.payment.PaymentDAO;
import model.billing.BillingAddress;
import model.customer.Customer;
import model.partner.Partner;
import model.payment.Payment;
import model.payment.Payment.PaymentType;
import service.payment.representation.PaymentBTRepresentation;
import service.payment.representation.PaymentCCRepresentation;
import service.payment.representation.PaymentPPRepresentation;
import service.payment.representation.PaymentRequest;
import service.payment.representation.PaymentRepresentationInterface;

public class PaymentActivity {
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public PaymentRepresentationInterface getCustomerPayment(String id) throws UnknownHostException {

		Customer cus = new Customer(id);

		Payment cuspayment = cus.getPayment();

		if (cuspayment.getPaymentType() == PaymentType.CC) {
			System.out.println(cuspayment.getCCexp());
			System.out.println(cuspayment.getCCnum());
			System.out.println(cuspayment.getCCsec());

			PaymentCCRepresentation cc = new PaymentCCRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setccnum(cuspayment.getCCnum());
			cc.setccsec(cuspayment.getCCsec());
			cc.setccexp(cuspayment.getCCexp());
			return cc;
		}

		if (cuspayment.getPaymentType() == PaymentType.BITCOIN) {
			PaymentBTRepresentation cc = new PaymentBTRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setbtacc(cuspayment.getBTacc());
			return cc;
		}

		if (cuspayment.getPaymentType() == PaymentType.PAYPAL) {
			PaymentPPRepresentation cc = new PaymentPPRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setppacc(cuspayment.getPPacc());
			return cc;
		}

		return new PaymentCCRepresentation();
	}
	
	public boolean deleteCustomerPayment(String id) throws UnknownHostException {
		
		Customer cus = new Customer(id);
		String payid = cus.getPId();
		cus.setPayment("");
		boolean s = cus.update();
		System.out.println(s);
		
		PaymentDAO db = PaymentDAO.getInstance();
		
		return db.deletePaymentById(payid);
	}
	
	public boolean updatePaymentCustomer(PaymentRequest req) throws UnknownHostException {
		if (req.getBilling().equals("")) {
			return false;
		}
		if (req.getUser().equals("")) {
			return false;
		}

		Customer cus = new Customer(req.getUser());
		
		String payid = cus.getPId();
		
		Payment newpayment = new Payment(payid);
		if (req.getType().equals("CC")) {
			newpayment.setPaymentType(PaymentType.CC);
			newpayment.setCCnum(req.getccnum());
			newpayment.setCCexp(req.getccexp());
			newpayment.setCCsec(req.getccsec());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("PP")) {
			newpayment.setPaymentType(PaymentType.PAYPAL);
			newpayment.setPPacc(req.getppacc());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("BT")) {
			newpayment.setPaymentType(PaymentType.BITCOIN);
			newpayment.setBTacc(req.getbtacc());
			newpayment.setBilling(req.getBilling());
		}

		newpayment.update();
		
		return true;
	}

	public boolean addPaymentCustomer(PaymentRequest req) throws UnknownHostException {
		if (req.getBilling().equals("")) {
			return false;
		}
		if (req.getUser().equals("")) {
			return false;
		}

		Payment newpayment = new Payment();
		if (req.getType().equals("CC")) {
			newpayment.setPaymentType(PaymentType.CC);
			newpayment.setCCnum(req.getccnum());
			newpayment.setCCexp(req.getccexp());
			newpayment.setCCsec(req.getccsec());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("PP")) {
			newpayment.setPaymentType(PaymentType.PAYPAL);
			newpayment.setPPacc(req.getppacc());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("BT")) {
			newpayment.setPaymentType(PaymentType.BITCOIN);
			newpayment.setBTacc(req.getbtacc());
			newpayment.setBilling(req.getBilling());
		}

		String payid = newpayment.save();

		Customer cus = new Customer(req.getUser());
		cus.setPayment(payid);

		return cus.update();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public PaymentRepresentationInterface getPartnerPayment(String id) throws UnknownHostException {

		Partner cus = new Partner(id);

		Payment cuspayment = cus.getPayment();

		if (cuspayment.getPaymentType() == PaymentType.CC) {
			System.out.println(cuspayment.getCCexp());
			System.out.println(cuspayment.getCCnum());
			System.out.println(cuspayment.getCCsec());

			PaymentCCRepresentation cc = new PaymentCCRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setccnum(cuspayment.getCCnum());
			cc.setccsec(cuspayment.getCCsec());
			cc.setccexp(cuspayment.getCCexp());
			return cc;
		}

		if (cuspayment.getPaymentType() == PaymentType.BITCOIN) {
			PaymentBTRepresentation cc = new PaymentBTRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setbtacc(cuspayment.getBTacc());
			return cc;
		}

		if (cuspayment.getPaymentType() == PaymentType.PAYPAL) {
			PaymentPPRepresentation cc = new PaymentPPRepresentation();
			BillingAddress bill = new BillingAddress(cuspayment.getBilling());
			cc.setBilling(bill.full());
			cc.setppacc(cuspayment.getPPacc());
			return cc;
		}

		return new PaymentCCRepresentation();
	}
	
	public boolean deletePartnerPayment(String id) throws UnknownHostException {
		
		Partner cus = new Partner(id);
		String payid = cus.getPId();
		cus.setPayment("");
		boolean s = cus.update();
		System.out.println(s);
		
		PaymentDAO db = PaymentDAO.getInstance();
		
		return db.deletePaymentById(payid);
	}
	
	public boolean updatePaymentPartner(PaymentRequest req) throws UnknownHostException {
		if (req.getBilling().equals("")) {
			return false;
		}
		if (req.getUser().equals("")) {
			return false;
		}

		Partner cus = new Partner(req.getUser());
		
		String payid = cus.getPId();
		
		Payment newpayment = new Payment(payid);
		if (req.getType().equals("CC")) {
			newpayment.setPaymentType(PaymentType.CC);
			newpayment.setCCnum(req.getccnum());
			newpayment.setCCexp(req.getccexp());
			newpayment.setCCsec(req.getccsec());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("PP")) {
			newpayment.setPaymentType(PaymentType.PAYPAL);
			newpayment.setPPacc(req.getppacc());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("BT")) {
			newpayment.setPaymentType(PaymentType.BITCOIN);
			newpayment.setBTacc(req.getbtacc());
			newpayment.setBilling(req.getBilling());
		}

		newpayment.update();
		
		return true;
	}

	public boolean addPaymentPartner(PaymentRequest req) throws UnknownHostException {
		if (req.getBilling().equals("")) {
			return false;
		}
		if (req.getUser().equals("")) {
			return false;
		}

		Payment newpayment = new Payment();
		if (req.getType().equals("CC")) {
			newpayment.setPaymentType(PaymentType.CC);
			newpayment.setCCnum(req.getccnum());
			newpayment.setCCexp(req.getccexp());
			newpayment.setCCsec(req.getccsec());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("PP")) {
			newpayment.setPaymentType(PaymentType.PAYPAL);
			newpayment.setPPacc(req.getppacc());
			newpayment.setBilling(req.getBilling());
		}
		if (req.getType().equals("BT")) {
			newpayment.setPaymentType(PaymentType.BITCOIN);
			newpayment.setBTacc(req.getbtacc());
			newpayment.setBilling(req.getBilling());
		}

		String payid = newpayment.save();

		Partner cus = new Partner(req.getUser());
		cus.setPayment(payid);

		return cus.update();
	}

}
