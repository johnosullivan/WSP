package client;



import java.security.SecureRandom;

import dal.order.OrderDAO;

public class App {

	
	static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( CHARS.charAt( rnd.nextInt(CHARS.length()) ) );
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
	
	public static void main(final String[] args) throws Exception {
						
		OrderDAO db = OrderDAO.getInstance();
		db.deleteOrderById("583f33ef9ba69cfc68ddd8a9");
		
	}

}