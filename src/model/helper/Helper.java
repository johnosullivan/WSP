package model.helper;

import model.order.Order.OrderStatusType;
import model.payment.Payment.PaymentType;
import java.security.SecureRandom;


public class Helper {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

	static public String[] splitEach(String s, int interval) {
	    int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
	    String[] result = new String[arrayLength];
	    int j = 0;
	    int lastIndex = result.length - 1;
	    for (int i = 0; i < lastIndex; i++) {
	        result[i] = s.substring(j, j + interval);
	        j += interval;
	    }
	    result[lastIndex] = s.substring(j);
	    return result;
	}
	
	public static String genComfirmation(int size, int split) {
		String data = randomString(size);
		String confirmstring = "";
		for (String str : splitEach(data,split)) {
			confirmstring = confirmstring + str + "-";
		}
		return confirmstring.substring(0, size + split);
	}
	
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
		case CREATED:
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
