package com.ws.project;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ws.project.address.Address;
//import com.ws.project.address.Address;
import com.ws.project.customer.Customer;
import com.ws.project.dao.CustomerDAO;
import com.ws.project.dao.MainDatabaseDAO;
import com.ws.project.dao.OrderDAO;
import com.ws.project.dao.PartnerDAO;
import com.ws.project.dao.ProductDAO;
import com.ws.project.dao.ReviewDAO;
import com.ws.project.order.Order;
import com.ws.project.order.OrderItem;
import com.ws.project.order.Order.OrderStatusType;
import com.ws.project.partner.Partner;
import com.ws.project.partner.PartnerOrder;
import com.ws.project.payment.Payment;
import com.ws.project.payment.Payment.PaymentType;
import com.ws.project.phone.Phone;
import com.ws.project.product.Product;
import com.ws.project.review.Review;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class AppTest { 
	static public void Space(String x) {
		System.out.println("[============================================================================================================]");
		System.out.println("JUnit Test: " + x);
	}
	//TESTS
	static Session session;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Space("setUpBeforeClass");
		System.out.println("Setup Database");
		session = Session.getInstance();
		System.out.println("Database setup done.");
		System.out.println("Creating products for search function.");
		//Creates the new partner and product for search test
		Partner partnerpost = new Partner();
		partnerpost.setFirst("Steve");
		partnerpost.setMiddle("Apple");
		partnerpost.setLast("Jobs");
		partnerpost.setCompany("Apple, Inc.");
		assertNotEquals(partnerpost.create(),"");		
		//Creates the new product 1
		Product productone = new Product();
		productone.setName("iPhone 5s");
		productone.setDescription("This is the iPhone 5s");
		productone.setCost(10000);
		productone.setCostCode("USD");
		productone.setSeller(partnerpost.getID());
		productone.setInventory(5);
		assertNotEquals(productone.create(),"");
		//Creates the new product 2
		Product producttwo = new Product();
		producttwo.setName("iPhone 6s");
		producttwo.setDescription("This is the iPhone 6s");
		producttwo.setCost(10000);
		producttwo.setCostCode("USD");
		producttwo.setSeller(producttwo.getID());
		producttwo.setInventory(5);
		assertNotEquals(producttwo.create(),"");
		//Creates the new product 3
		Product productthree = new Product();
		productthree.setName("iPhone 7 plus");
		productthree.setDescription("This is the iPhone 7 plus");
		productthree.setCost(10000);
		productthree.setCostCode("USD");
		productthree.setSeller(producttwo.getID());
		productthree.setInventory(5);
		assertNotEquals(productthree.create(),"");
		//Creates the new product 4
		Product productfour = new Product();
		productfour.setName("iPad 2");
		productfour.setDescription("This is the iPad 2");
		productfour.setCost(10000);
		productfour.setCostCode("USD");
		productfour.setSeller(producttwo.getID());
		productfour.setInventory(5);
		assertNotEquals(productfour.create(),"");
		System.out.println("Creating products for search complete.");
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Space("tearDownAfterClass");
		// Empty the database and closes the database connection
		System.out.println("Tear down tests"); 
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		db.dropCollection();
		db.close();
		System.out.println("Dropped all collection and closed database connection.");
		System.out.println("*ALL TESTS COMPLETED!*");
	}

	@Test
	public void SearchTest() throws Exception {
		Space("Search Test");
		//Gets all the results 
		System.out.println("Starts the search test for the search term iphone");
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		ArrayList<Product> results = db.searchService("iPhone");
		//There should be 3 results that all the results (4 went entered in tempdb)
		assertEquals(results.size(),3);
		System.out.println("Search test completed.");
	}
	
	@Test
	public void AddressTests() throws Exception {
		Space("AddressTests");
		//Test to create a user and save their info in database
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		customerpost.setEmail("jnosullivan@mac.com");
		assertNotEquals(customerpost.create(),"");	
		
		Address addressone = new Address();
		addressone.setAddress("123 ABC Ln");
		addressone.setCity("Wilimette");
		addressone.setState("IL");
		addressone.setZip(600091);
		
		Address addresstwo = new Address();
		addresstwo.setAddress("456 ABC Ln");
		addresstwo.setCity("Evanston");
		addresstwo.setState("IL");
		addresstwo.setZip(600091);
		
		customerpost.addAddress(addressone);
		customerpost.addAddress(addresstwo);
		
		ArrayList<Address> array = customerpost.getAllAddress();
		
		for (Address a : array) {
			System.out.println(a.getAddress());
		}
		
		
		
	}
	
	@Test
	public void PhoneTests() throws Exception {
		Space("PhoneTests");
		//Test to create a user and save their info in database
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		customerpost.setEmail("jnosullivan@mac.com");
		assertNotEquals(customerpost.create(),"");	
		
		Phone phoneone = new Phone();
		phoneone.setType("Home");
		phoneone.setPhone("12379846");
	
		Phone phonetwo = new Phone();
		phonetwo.setType("Work");
		phonetwo.setPhone("93875223");
		
		customerpost.addPhone(phoneone);
		customerpost.addPhone(phonetwo);

		
		ArrayList<Phone> array = customerpost.getAllPhone();
		
		for (Phone a : array) {
			System.out.println(a.getType() + " :" + a.getPhone());
		}
		
		
		
	}
	
	
	@Test
	public void RegisterTest() throws Exception {
		Space("Register Test");
		System.out.println("Test register user.");
		//Test to create a user and save their info in database
		boolean status = session.register("jnosullivan1", "WebServicesRocks","jnosullivan@mac.com","8473457023","22","John O'Sullivan");
		assertEquals(status,true);
		System.out.println("Done with test registering user.");
	}
	
	@Test
	public void LoginTest() throws Exception {
		Space("Login Test");
		//Test to login
		System.out.println("Test login user.");
		boolean status = session.login("jnosullivan1", "WebServicesRocks");
		assertEquals(status,true);
		MainDatabaseDAO db = MainDatabaseDAO.getInstance();
		db.deleteUser("jnosullivan1");
		System.out.println("Delete test user. / Cleaning database");
	}
	
	@Test
	public void CustomerFullProfile() throws UnknownHostException {
		Space("Customer Full Profile");
		System.out.println("CustomerFullProfile begin.");
		String customeridtest;
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		customerpost.setEmail("jnosullivan@mac.com");
		//customerpost.setPhone("8473457023");
		customerpost.setPP("http://google.com/pic/somepicture.png");
		customeridtest = customerpost.create();
		assertNotEquals(customeridtest,"");	
		System.out.println("Created customer. Testing database entry...");
		Customer customerget = new Customer(customeridtest);
		assertEquals(customerget.getFirst(),"John");
		assertEquals(customerget.getMiddle(),"Nikolas");
		assertEquals(customerget.getLast(),"O'Sullivan");
		assertEquals(customerget.getEmail(),"jnosullivan@mac.com");
		//assertEquals(customerget.getPhone(),"8473457023");
		assertEquals(customerget.getPP(),"http://google.com/pic/somepicture.png");
		//Uses the customer id to delete the document in the collection (DELETE TEST)
		//assertEquals(db.deleteCustomerById(customeridtest),true);
		System.out.println("Deleted customer data / Clean database.");
	}
	
	@Test
	public void PartnerFullProfile() throws UnknownHostException {
		Space("Partner Full Profile");
		System.out.println("PartnerFullProfile begin.");
		String partneridtest;
		Partner partnerpost = new Partner();
		partnerpost.setFirst("Maria");
		partnerpost.setMiddle("Emma");
		partnerpost.setLast("O'Sullivan");
		partnerpost.setCompany("Apple, Inc.");
		partnerpost.setHomepage("http://www.apple.com");
		//partnerpost.setPhone("8472567178");
		partnerpost.setPP("http://google.com/pic/somepicture.png");
		partnerpost.setEmail("apple@gmail.com");
		partneridtest = partnerpost.create();
		assertNotEquals(partneridtest,"");	
		System.out.println("Created a new partner. Testing database entry...");
		Partner partnerget = new Partner(partneridtest);
		assertEquals(partnerget.getFirst(),"Maria");
		assertEquals(partnerget.getMiddle(),"Emma");
		assertEquals(partnerget.getLast(),"O'Sullivan");
		assertEquals(partnerget.getEmail(),"apple@gmail.com");
		assertEquals(partnerget.getHomepage(),"http://www.apple.com");
		assertEquals(partnerget.getCompany(),"Apple, Inc.");
		assertEquals(partnerget.getPP(),"http://google.com/pic/somepicture.png");
		//assertEquals(partnerget.getPhone(),"8472567178");
		PartnerDAO db = PartnerDAO.getInstance();
		assertEquals(db.deletePartnerById(partneridtest),true);
		System.out.println("Delete partner data clean database.");
	}
	
	
	@Test
	public void CustomerTest() throws UnknownHostException {
		Space("Customer Test Basic");
		System.out.println("CustomerTest begin.");
		//Creates a string to hold the customer id for this test
		String customeridtest;
		//Creates the new Customer (POST METHOD TEST)
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		customeridtest = customerpost.create();
		assertNotEquals(customeridtest,"");	
		System.out.println("Created customer. Testing database entry...");		
		//Get Customer Checks Data (GET METHOD TEST)
		Customer customerget = new Customer(customeridtest);
		assertEquals(customerget.getFirst(),"John");
		assertEquals(customerget.getMiddle(),"Nikolas");
		assertEquals(customerget.getLast(),"O'Sullivan");
		//Customer changes value and updates (PUT METHOD TEST)
		customerget.setFirst("Johnny");
		customerget.setMiddle("SomeMiddle");
		customerget.setLast("Jones");
		assertEquals(customerget.update(),true);
		System.out.println("Updated customer info.");
		//Get Customer Checks Data After Put (GET AFTER PUT METHOD TEST)
		Customer customergetafter = new Customer(customeridtest);
		assertEquals(customergetafter.getFirst(),"Johnny");
		assertEquals(customergetafter.getMiddle(),"SomeMiddle");
		assertEquals(customergetafter.getLast(),"Jones");
		System.out.println("Checked saved updated customer data.");
		//Uses the customer id to delete the document in the collection (DELETE TEST)
		CustomerDAO db = CustomerDAO.getInstance();
		assertEquals(db.deleteCustomerById(customeridtest),true);
		System.out.println("Deleted customer data / Clean database.");
	}

	@Test
	public void PartnerTest() throws UnknownHostException {
		Space("Partner Test");
		//Creates a string to hold the partner id for this test
	    String partneridtest;
	    System.out.println("Started Partner Test.");
		//Creates the new partner (POST METHOD TEST)
		Partner partnerpost = new Partner();
		partnerpost.setFirst("Maria");
		partnerpost.setMiddle("Emma");
		partnerpost.setLast("O'Sullivan");
		partnerpost.setCompany("Apple, Inc.");
		partneridtest = partnerpost.create();
		assertNotEquals(partneridtest,"");	
		System.out.println("Created a new partner. Testing database entry...");
		//Get partner Checks Data (GET METHOD TEST)
		Partner partnerget = new Partner(partneridtest);
		assertEquals(partnerget.getFirst(),"Maria");
		assertEquals(partnerget.getMiddle(),"Emma");
		assertEquals(partnerget.getLast(),"O'Sullivan");
		//Customer changes value and updates (PUT METHOD TEST)
		partnerget.setFirst("Mimmy");
		partnerget.setMiddle("NoMiddle");
		partnerget.setLast("Kimmy");
		assertEquals(partnerget.update(),true);
		System.out.println("Checked the updted partner data.");
		//Get partner Checks Data After Put (GET AFTER PUT METHOD TEST)
		Partner partnergetafter = new Partner(partneridtest);
		assertEquals(partnergetafter.getFirst(),"Mimmy");
		assertEquals(partnergetafter.getMiddle(),"NoMiddle");
		assertEquals(partnergetafter.getLast(),"Kimmy");
		//Uses the partner id to delete the document in the collection (DELETE TEST)
		PartnerDAO db = PartnerDAO.getInstance();
		assertEquals(db.deletePartnerById(partneridtest),true);
		System.out.println("Delete partner data clean database.");
	}
	
	@Test
	public void ProductTest() throws UnknownHostException {
		Space("Product Test");
		//Creates a string to hold the partner id for this test
	    String partneridtest;
	    String productidtest;
	    System.out.println("Started product create test");
	    //Creates the new partner (POST METHOD TEST)
	  	Partner partnerpost = new Partner();
	  	partnerpost.setFirst("Maria");
	  	partnerpost.setMiddle("Emma");
	  	partnerpost.setLast("O'Sullivan");
	  	partnerpost.setCompany("MEO, Inc.");
	  	partneridtest = partnerpost.create();
	  	assertNotEquals(partneridtest,"");
	  	System.out.println("First created a new partner.");
	    //Creates the new product (POST METHOD TEST)
		Product productone = new Product();
		productone.setName("iPhone");
		productone.setDescription("Phone LOL Buy it");
		productone.setCost(10000);
		productone.setCostCode("USD");
		productone.setSeller(partneridtest);
		productone.setInventory(5);
		productidtest = productone.create();
		assertNotEquals(productidtest,"");
		System.out.println("Created a new product with partner link.");
		//Get product Checks Data (GET METHOD TEST)
		Product productget1 = new Product(productidtest);
		assertEquals(productget1.getName(),"iPhone");
		assertEquals(productget1.getDescription(),"Phone LOL Buy it");
		assertEquals(productget1.getCost(),10000);
		assertEquals(productget1.getInventory(),5);
		System.out.println("Get Method Test checking data.");
		//Updated the product info and saved it (PUT METHOD TEST)
		productone.setName("iPhone 7 Plus");
		assertEquals(productone.update(),true);
		System.out.println("Test selling more that is in stock.");
		//Product try to buy 6 of 5, its false.
		assertEquals(productone.sold(6),false);
		System.out.println("Test selling 3 of 5 products in stock.");
		//Product 3 sold so updates inventory and update
		assertEquals(productone.sold(3),true);
		System.out.println("Test selling 2 of 2 products left in stock.");
		//Product 2 sold so updates inventory and update
		assertEquals(productone.sold(2),true);
		System.out.println("Checks to make sure the product is unavailable");
		//Product checks if unavailable
		assertEquals(productone.getAvailable(),false);
		//Get product Checks Data (GET METHOD TEST)
		Product productget2 = new Product(productidtest);
		assertEquals(productget2.getName(),"iPhone 7 Plus");
		assertEquals(productget2.getDescription(),"Phone LOL Buy it");
		assertEquals(productget2.getCost(),10000);
		assertEquals(productget2.getInventory(),0);
		//Uses the partner id to delete the document in the collection (DELETE TEST)
		PartnerDAO db = PartnerDAO.getInstance();
		assertEquals(db.deletePartnerById(partneridtest),true);
		//Uses the product id to delete the document in the collection (DELETE TEST)
		ProductDAO dbp = ProductDAO.getInstance();
		assertEquals(dbp.deleteProductById(productidtest),true);
		System.out.println("Deletes Test data and cleans database.");
	}
	
	@Test 
	public void OrderTest() throws UnknownHostException {
		Space("OrderTest With processing, shipping, and comfirmation.");
		//Creates a string to hold the partner id for this test
	    String partneridtest;
	    String productidtest;
	    String customeridtest;
	    System.out.println("Starting Order Test 1");
		//Creates the new Customer (POST METHOD TEST)
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		Payment payc = new Payment();
		payc.setPaymentType(PaymentType.PAYPAL);
		//payc.setBilling("232 Chicago Wilmette Ln 60660");
		customerpost.setPayment(payc);
		customeridtest = customerpost.create();
		assertNotEquals(customeridtest,"");	
		System.out.println("Creatomg Partner and Product for Test");
	    //Creates the new partner (POST METHOD TEST)
	  	Partner partnerpost = new Partner();
	  	partnerpost.setFirst("Maria");
	  	partnerpost.setMiddle("Emma");
	  	partnerpost.setLast("O'Sullivan");
	  	partnerpost.setCompany("MEO, Inc.");
	  	Payment payp = new Payment();
		payp.setPaymentType(PaymentType.PAYPAL);
		//payp.setBilling("723 Ouilmette Wilmette Ln 60091");
		partnerpost.setPayment(payp);
	  	partneridtest = partnerpost.create();
	  	assertNotEquals(partneridtest,"");
	    //Creates the new product (POST METHOD TEST)
		Product productone = new Product();
		productone.setName("iPhone 7 Plus");
		productone.setDescription("Phone LOL Buy it");
		productone.setCost(1000);
		productone.setCostCode("USD");
		productone.setSeller(partneridtest);
		productone.setInventory(5);
		productidtest = productone.create();
		assertNotEquals(productidtest,"");
		System.out.println("Creates order with one product in cart.");
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();
		OrderItem oitem1 = new OrderItem();
		oitem1.setProduct(productone);
		oitem1.setQuantity(2);
		carts.add(oitem1);
		System.out.println("Creates the order object.");
		Order orderone = new Order();
		orderone.setBuyer(customerpost);
		orderone.setProducts(carts);
		orderone.setShipping("723 Ouilmette Wilmette Ln 60091");
		assertEquals(orderone.total(),2000);
		System.out.println("Process the order and creates database entry.");
		String orderonetest = orderone.process();
		assertNotEquals(orderonetest,"");
		System.out.println("Check customer order GET method.");
		ArrayList<Order> myorders = customerpost.getMyOrder();
		System.out.println("My Orders: " + customerpost.fullName());
		for (Order temporder: myorders) {
			System.out.println("==============");
			System.out.println("Order: " + temporder.getID());
			System.out.println("Shipping Address: " + temporder.getShipping());
			for (OrderItem otemp: temporder.getProducts()) {
				System.out.println(otemp.getProduct().getName() + " - Q: " + otemp.getQuantity());
			}
		}
		System.out.println("==============");
		System.out.println("Done checking customer my orders.");
		System.out.println("Partner test of the order.");
		//The partner get its order to ship
		ArrayList<PartnerOrder> data = partnerpost.getOrders();
		System.out.println("Partner get orders that need to be shipped.");
		for (PartnerOrder po: data) {
			System.out.println("==============");
			System.out.println("Order: " + po.getOrder().getID() + "Status (" + po.getOrder().orderStatusString() + ") / Sent to: " + po.getOrder().getBuyer().fullName() + "/ Shipping: " + po.getOrder().getShipping());
			for (OrderItem otemp: po.getOrderedItems()) {
				System.out.println(otemp.getProduct().getName() + " - Q: " + otemp.getQuantity());
			}
			System.out.println("Simulate the order being shipped and updating the order#: " + po.getOrder().getID());
			po.getOrder().productsShipped(po.getOrderedItems());
			System.out.println("Order has been shipped and updated by partner.");
		}
		System.out.println("==============");
		System.out.println("Creating new order object to checking status. ID: " + orderonetest);
		Order neworderobject = new Order(orderonetest);
		assertEquals(neworderobject.getOrderStatus(),OrderStatusType.SHIPPED);
		System.out.println("Trying to cancel its fails since its shipped already.");
		assertEquals(neworderobject.cancelOrder(),false);
		System.out.println("(Status Changed) Order has been comfirmed as shipped by the partners of the products in order.");
		//Tests Report
		System.out.println("Calls the product and creates an report.");
		ArrayList<String> newreport = productone.getReport().getSummary();
		System.out.println("Report Start ============== Product: " + productone.getName());
		for (String str: newreport) {
			System.out.println(str);
		}
		System.out.println("Report Ended ============== Product: " + productone.getName());
		//Uses the customer id to delete the document in the collection (DELETE TEST)
		CustomerDAO db = CustomerDAO.getInstance();
		assertEquals(db.deleteCustomerById(customeridtest),true);
		//Uses the partner id to delete the document in the collection (DELETE TEST)
		PartnerDAO dbp = PartnerDAO.getInstance();
		assertEquals(dbp.deletePartnerById(partneridtest),true);
		//Uses the product id to delete the document in the collection (DELETE TEST)
		ProductDAO dbpp = ProductDAO.getInstance();
		assertEquals(dbpp.deleteProductById(productidtest),true);
		//Uses the order id to delete the document in the collection (DELETE TEST)
		OrderDAO dbo = OrderDAO.getInstance();
		assertEquals(dbo.deleteOrderById(orderonetest),true);
		System.out.println("Delete the test data and clean up the database.");
	}
	
	@Test 
	public void OrderTestCancel() throws UnknownHostException {
		Space("Order Test Cancel With processing, cancelling, and inventory restock.");
		//Creates a string to hold the partner id for this test
	    String partneridtest;
	    String productidtest;
	    String customeridtest;
	    System.out.println("Starting Order Test 2");
		//Creates the new Customer (POST METHOD TEST)
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		Payment payc = new Payment();
		payc.setPaymentType(PaymentType.PAYPAL);
		//payc.setBilling("232 Chicago Wilmette Ln 60660");
		customerpost.setPayment(payc);
		customeridtest = customerpost.create();
		assertNotEquals(customeridtest,"");	
		System.out.println("Creating Partner and Product for Test");
	    //Creates the new partner (POST METHOD TEST)
	  	Partner partnerpost = new Partner();
	  	partnerpost.setFirst("Maria");
	  	partnerpost.setMiddle("Emma");
	  	partnerpost.setLast("O'Sullivan");
	  	partnerpost.setCompany("Apple, Inc.");
	  	Payment payp = new Payment();
		payp.setPaymentType(PaymentType.PAYPAL);
		//payp.setBilling("723 Ouilmette Wilmette Ln 60091");
		partnerpost.setPayment(payp);
	  	partneridtest = partnerpost.create();
	  	assertNotEquals(partneridtest,"");
	    //Creates the new product (POST METHOD TEST)
		Product productone = new Product();
		productone.setName("iPhone 7 Plus");
		productone.setDescription("Phone LOL Buy it");
		productone.setCost(1000);
		productone.setCostCode("USD");
		productone.setSeller(partneridtest);
		productone.setInventory(5);
		productidtest = productone.create();
		assertNotEquals(productidtest,"");
		System.out.println("Creates order with one product in cart.");
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();
		OrderItem oitem1 = new OrderItem();
		oitem1.setProduct(productone);
		oitem1.setQuantity(2);
		carts.add(oitem1);
		System.out.println("Creates the order object.");
		Order orderone = new Order();
		orderone.setBuyer(customerpost);
		orderone.setProducts(carts);
		orderone.setShipping("723 Ouilmette Wilmette Ln 60091");
		assertEquals(orderone.total(),2000);
		System.out.println("Process the order and creates database entry.");
		String orderonetest = orderone.process();
		assertNotEquals(orderonetest,"");
		System.out.println("Check product inventory after processing order.");
		Product producttesttwo = new Product(productidtest);
		assertEquals(producttesttwo.getInventory(),3);
		System.out.println("Check customer order GET method.");
		ArrayList<Order> myorders = customerpost.getMyOrder();
		System.out.println("My Orders: " + customerpost.fullName());
		for (Order temporder: myorders) {
			System.out.println("==============");
			System.out.println("Order: " + temporder.getID());
			System.out.println("Shipping Address: " + temporder.getShipping());
			for (OrderItem otemp: temporder.getProducts()) {
				System.out.println(otemp.getProduct().getName() + " - Q: " + otemp.getQuantity());
			}
		}
		System.out.println("==============");
		System.out.println("Done checking customer my orders.");
		System.out.println("Partner test of the order.");
		//The partner get its order to ship
		ArrayList<PartnerOrder> data = partnerpost.getOrders();
		System.out.println("Partner get orders that need to be shipped.");
		for (PartnerOrder po: data) {
			System.out.println("==============");
			System.out.println("Order: " + po.getOrder().getID() + "Status (" + po.getOrder().orderStatusString() + ") / Sent to: " + po.getOrder().getBuyer().fullName() + "/ Shipping: " + po.getOrder().getShipping());
			for (OrderItem otemp: po.getOrderedItems()) {
				System.out.println(otemp.getProduct().getName() + " - Q: " + otemp.getQuantity());
			}
		}
		System.out.println("All order are receivied on both ends");
		System.out.println("==============");
		Order neworderobject = new Order(orderonetest);
		assertEquals(neworderobject.getOrderStatus(),OrderStatusType.PROCESSED);
		assertEquals(neworderobject.cancelOrder(),true);
		System.out.println("Order Cancelled, Checking status and inventory...");
		System.out.println("Inventory Check : Creates new product object to check inventory (5)");
		Product producttestthree = new Product(productidtest);
		assertEquals(producttestthree.getInventory(),5);
		System.out.println("Checks Orders : For Customer and Partner Side that they are cancelled.");
		System.out.println("Checks Orders : Customer Orders");
		ArrayList<Order> checkmyorders = customerpost.getMyOrder();
		for (Order temporder: checkmyorders) {
			System.out.println("==============");
			System.out.println("Order#: " + temporder.getID() + " Status (" + temporder.orderStatusString() + ")");
		}
		System.out.println("==============");
		System.out.println("Checks Orders : Partners Orders");
		ArrayList<PartnerOrder> checkpdata = partnerpost.getOrders();
		for (PartnerOrder po: checkpdata) {
			System.out.println("==============");
			System.out.println("Order#: " + po.getOrder().getID() + " Status (" + po.getOrder().orderStatusString() + ")");
		}
		System.out.println("==============");
		System.out.println("Checks Orders Done.");	
		CustomerDAO db = CustomerDAO.getInstance();
		assertEquals(db.deleteCustomerById(customeridtest),true);
		//Uses the partner id to delete the document in the collection (DELETE TEST)
		PartnerDAO dbp = PartnerDAO.getInstance();
		assertEquals(dbp.deletePartnerById(partneridtest),true);
		//Uses the product id to delete the document in the collection (DELETE TEST)
		ProductDAO dbpp = ProductDAO.getInstance();
		assertEquals(dbpp.deleteProductById(productidtest),true);
		System.out.println("Delete the test data and clean up the database.");
	}
	
	@Test 
	public void ReviewTest() throws UnknownHostException {
		Space("Review Test will do the OrderTest simulate product delivered and reviewer.");
		//Creates a string to hold the partner id for this test
	    String partneridtest;
	    String productidtestnotb;
	    String productidtest;
	    String customeridtest;
		//Creates the new Customer (POST METHOD TEST)
		Customer customerpost = new Customer();
		customerpost.setFirst("John");
		customerpost.setMiddle("Nikolas");
		customerpost.setLast("O'Sullivan");
		Payment payc = new Payment();
		payc.setPaymentType(PaymentType.PAYPAL);
		//payc.setBilling("232 Chicago Wilmette Ln 60660");
		customerpost.setPayment(payc);
		customeridtest = customerpost.create();
		assertNotEquals(customeridtest,"");	
	    //Creates the new partner (POST METHOD TEST)
	  	Partner partnerpost = new Partner();
	  	partnerpost.setFirst("Maria");
	  	partnerpost.setMiddle("Emma");
	  	partnerpost.setLast("O'Sullivan");
	  	partnerpost.setCompany("MEO, Inc.");
	  	Payment payp = new Payment();
		payp.setPaymentType(PaymentType.PAYPAL);
		//payp.setBilling("723 Ouilmette Wilmette Ln 60091");
		partnerpost.setPayment(payp);
	  	partneridtest = partnerpost.create();
	  	assertNotEquals(partneridtest,"");
	    //Creates the new product (POST METHOD TEST)
	  	System.out.println("Creates Product that will be purchased.");
		Product productone = new Product();
		productone.setName("iPhone 7 Plus");
		productone.setDescription("Phone LOL Buy it");
		productone.setCost(1000);
		productone.setCostCode("USD");
		productone.setSeller(partneridtest);
		productone.setInventory(5);
		productidtest = productone.create();
		assertNotEquals(productidtest,"");
		System.out.println("Create Product that will not be purchased. Catch Fake Reviews.");
		Product producttwo = new Product();
		producttwo.setName("iPhone 6 Plus");
		producttwo.setDescription("Phone LOL Buy it");
		producttwo.setCost(1000);
		producttwo.setCostCode("USD");
		producttwo.setSeller(partneridtest);
		producttwo.setInventory(5);
		productidtestnotb = producttwo.create();
		assertNotEquals(productidtestnotb,"");
		ArrayList<OrderItem> carts = new ArrayList<OrderItem>();
		OrderItem oitem1 = new OrderItem();
		oitem1.setProduct(productone);
		oitem1.setQuantity(2);
		carts.add(oitem1);
		Order orderone = new Order();
		orderone.setBuyer(customerpost);
		orderone.setProducts(carts);
		orderone.setShipping("723 Ouilmette Wilmette Ln 60091");
		assertEquals(orderone.total(),2000);
		String orderonetest = orderone.process();
		assertNotEquals(orderonetest,"");
		Order neworderobject = new Order(orderonetest);
		assertEquals(neworderobject.getOrderStatus(),OrderStatusType.PROCESSED);
		System.out.println("The test starts by updating the order has been Delivered.");
		assertEquals(neworderobject.productsDelivered(),true);
		Order neworderobjecttwo = new Order(orderonetest);
		System.out.println("Create a review for the tests.");
		Review review = new Review();
		review.setOrder(orderonetest);
		review.setStars(4);
		review.setProduct(productidtestnotb);
		review.setReview("This is a review of a iphone that was bought from Lakeshore MarketPlace.");
		System.out.println("Submit review for product not bought check fake review.");
		assertEquals(review.submit(),"");
		System.out.println("Submit review for product not delivered.");
		review.setProduct(productidtest);
		assertEquals(neworderobjecttwo.getOrderStatus(),OrderStatusType.DELIVERED);
		System.out.println("Set order of test product to delivered and submit review");
		String reviewid = review.submit();
		assertNotEquals(reviewid,"");
		System.out.println("Get Test to get reviews for a product to check.");
		Review checkreview = new Review(reviewid);
		assertEquals(checkreview.getReview(),"This is a review of a iphone that was bought from Lakeshore MarketPlace.");
		assertEquals(checkreview.getOrder(),orderonetest);
		assertEquals(checkreview.getStars(),4);
		assertEquals(checkreview.getProduct(),productidtest);
		System.out.println("Review Get Method Checked Done.");
		checkreview.setStars(5);
		assertEquals(checkreview.update(),true);
		System.out.println("Review Edited and Saved.");
		Review checkreviewfinal = new Review(reviewid);
		assertEquals(checkreviewfinal.getStars(),5);
		System.out.println("Review test finished.");
		ReviewDAO db = ReviewDAO.getInstance();
		assertEquals(db.deleteReview(reviewid),true);
		//Uses the review id to delete the document in collection (DELETE TEST)
		CustomerDAO dbc = CustomerDAO.getInstance();
		assertEquals(dbc.deleteCustomerById(customeridtest),true);
		//Uses the partner id to delete the document in the collection (DELETE TEST)
		PartnerDAO dbp = PartnerDAO.getInstance();
		assertEquals(dbp.deletePartnerById(partneridtest),true);
		//Uses the product id to delete the document in the collection (DELETE TEST)
		ProductDAO dbro = ProductDAO.getInstance();
		assertEquals(dbro.deleteProductById(productidtest),true);
		assertEquals(dbro.deleteProductById(productidtestnotb),true);
		//Uses the order id to delete the document in the collection (DELETE TEST)
		OrderDAO dbor = OrderDAO.getInstance();
		assertEquals(dbor.deleteOrderById(orderonetest),true);
		System.out.println("Delete the test data and clean up the database.");
	}	
}
