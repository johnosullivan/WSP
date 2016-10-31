package model.helper;

import model.order.Order.OrderStatusType;
import model.payment.Payment.PaymentType;

public class Helper {
	public static int codeForPayment(PaymentType type) {
		switch (type) {
		case CC:
			return 1;
		case PAYPAL:
			return 2;
		case BITCOIN:
			return 3;
		default:
			break;
		}
		return 0;
	}

	public static int statuscode(OrderStatusType ord) {
		switch (ord) {
		case PROCESSING:
			return 1;
		case PROCESSED:
			return 2;
		case SHIPPED:
			return 3;
		case INROUTE:
			return 4;
		case DELIVERED:
			return 5;
		case CANCELED:
			return 6;
		case PAYMENTFAILED:
			return 7;
		default:
			break;
		}
		return 0;
	}
}
