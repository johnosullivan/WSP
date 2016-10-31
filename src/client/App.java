package client;

import model.partner.Partner;
import model.product.Product;
import static org.junit.Assert.*;

public class App {

	public static void main(final String[] args) throws Exception {
		Partner partnerpost = new Partner();
		partnerpost.setFirst("Steve");
		partnerpost.setMiddle("Apple");
		partnerpost.setLast("Jobs");
		partnerpost.setCompany("Apple, Inc.");
		assertNotEquals(partnerpost.create(), "");
		// Creates the new product 1
		Product productone = new Product();
		productone.setName("iPhone 5s");
		productone.setDescription("This is the iPhone 5s");
		productone.setCost(10000);
		productone.setCostCode("USD");
		productone.setSeller(partnerpost.getID());
		productone.setInventory(5);
		assertNotEquals(productone.create(), "");
		// Creates the new product 2
		Product producttwo = new Product();
		producttwo.setName("iPhone 6s");
		producttwo.setDescription("This is the iPhone 6s");
		producttwo.setCost(10000);
		producttwo.setCostCode("USD");
		producttwo.setSeller(partnerpost.getID());
		producttwo.setInventory(5);
		assertNotEquals(producttwo.create(), "");
		// Creates the new product 3
		Product productthree = new Product();
		productthree.setName("iPhone 7 plus");
		productthree.setDescription("This is the iPhone 7 plus");
		productthree.setCost(10000);
		productthree.setCostCode("USD");
		productthree.setSeller(partnerpost.getID());
		productthree.setInventory(5);
		assertNotEquals(productthree.create(), "");
		// Creates the new product 4
		Product productfour = new Product();
		productfour.setName("iPad 2");
		productfour.setDescription("This is the iPad 2");
		productfour.setCost(10000);
		productfour.setCostCode("USD");
		productfour.setSeller(partnerpost.getID());
		productfour.setInventory(5);
		assertNotEquals(productfour.create(), "");
	}

}