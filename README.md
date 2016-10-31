# Web Services Project (Fall 2016) 

By John O'Sullivan

=============================================================================================================================

For project I decided to go the no-sql way. (MongoDB)

1) Install MongoDB at https://docs.mongodb.com/manual/installation/

2) Once installed launched the monogd server.

3) Run the AppTest.java as a JUnit Test.

Supporting Libraries Needed For Java Project (Located in Jars folder at root)

- bson-2.13.3.jar
- hamcrest-core-1.3.jar
- junit-4.12.jar
- mongo-java-driver-2.13.3.jar
- Apache CXF Library [3.1.7]
- Apache Tomcat 9.0
- JRE System Library [Java SE 8 [1.8.0_101]]

=============================================================================================================================

# API ApacheCXF/Tomcat  

Hosted: <a>https://fall2016wsp.herokuapp.com/api/</a>

Note: This is running on a free dyno thus it is unactive. Requests will initially take longer to allow server to boot up.

## Customer

###### Customer Object

* POST /customerservice/customer

```
```

* PUT /customerservice/customer

```
```

* GET /customerservice/customer/{id}

```
```

* DELETE /customerservice/customer/{id}

```
```
###### Customer's Phone Object


* POST /customerservice/customer/phone

```
```

* PUT /customerservice/customer/phone

```
```

* GET /customerservice/customer/phone/{id}

```
```

* DELETE /customerservice/customer/phone/{id}

```
```

###### Customer's Address Object

* POST /customerservice/customer/address

```
```

* PUT /customerservice/customer/address

```
```

* GET /customerservice/customer/address/{id}

```
```

* DELETE /customerservice/customer/address/{id}

```
```


## Partner

#### Partner Object

* POST /customerservice/partner

```
```

* PUT /customerservice/partner

```
```

* GET /customerservice/partner/{id}

```
```

* DELETE /customerservice/partner/{id}

```
```

###### Partner's Phone Object

* POST /partnerservice/partner/phone

```
```

* PUT /partnerservice/partner/phone

```
```

* GET /partnerservice/partner/phone/{id}

```
```

* DELETE /partnerservice/partner/phone/{id}

```
```

###### Partner's Address Object

* POST /partnerservice/partner/address

```
```

* PUT /partnerservice/partner/address

```
```

* GET /partnerservice/partner/address/{id}

```
```

* DELETE /partnerservice/partner/address/{id}

```
```


## Product

###### Product Object

* POST /productservice/product

```
```

* GET /productservice/product/{id}

```
```

###### Product Search

* POST /productservice/search

```
```

## Order

* POST /orderservice/order

```
```

# Documentation

Features:

- Search in the database.
- Create edit/remove/update products, partners, and customers.
- Create orders and check status. Edit orders. 
- Accept order. Ship orders. 
- Track payment and create reports for products.
- Cancel order, refund, restock the product's inventory.
- Review products purchased by customers. 

For detailed documentation of our architecture and implementation. View <a href="https://github.com/johnosullivan/WSP/blob/master/Documentation.md">Here</a>. UML diagram <a href="https://github.com/johnosullivan/WSP/blob/master/diagram.png">Here</a>


=============================================================================================================================

# The JUnit Tests 

#### (Test Log <a href="https://github.com/johnosullivan/WSP/blob/master/JUnitConsoleLog.txt">JUnitConsoleLog.txt</a>)

### public void PhoneTests / AddressTests

This test to show the phone and address entity separate from the customer object.

### public void Billing()

This test will create an address object and save it to a customer. So customers can have many address on the site. Then create a payment object to which is sets the address as billing.

### public void SearchTest() 

This test will use the key word "iphone" to do a query in the database for all product that  have to do with iphone. The product where entered in the database before tests where started. And deleted at teardown.

### public void RegisterTest() throws Exception 

This is a simple test to register a user and make sure it return a user id. This id can then be used in the finished product to let a user be a customer as well as a seller like Amazon.

### public void LoginTest() throws Exception

This is a login test to make sure the username and password are valid and allows a login session

### public void CustomerFullProfile() 

This test creates a customers full profile including first, middle, last, email, phone, and propic.

### public void PartnerFullProfile() 

This test creates a partner full profile including first, middle, last, email, phone, propic, homepage, and payment.

### public void CustomerTest() 

This is a simple test of customer object with just name.  This test get the object from database to make sure it was entered correctly.

### public void PartnerTest()

This is a simple test of partner object with just name.  This test get the object from database to make sure it was entered correctly.

### public void ProductTest()

This is a simple test of partners object with just name and a product that the partner is linked too. This test get the object from database to make sure it was entered correctly.

### public void OrderTest()

This is a full test of a order. Customer and Partner with products are created. The customer creates a order with a product of partner. The order is process and status is checked on both ends. A simulated shipped of product of partner is done and status is checked yet again. Also gets the report for the product.

### public void OrderTestCancel()

The order test cancel is the same of the last test but the customer cancel the order and the status is changed on both ends for the customer and partenr without deleting the order. The product in the order and returned to the inventory within the product object.

### public void ReviewTest()

The review test create two product and one order with one of them. The order is processed and simulated as shipped and delivered. The review test check to make sure the reviewer got the product before allowing the submission as well as checking if the customer bought the product in the first place.


=============================================================================================================================










