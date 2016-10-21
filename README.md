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

# API ApacheCXF/Tomcat (IN DEV)

## Customer

##### POST /customerservice/customer
```
Payload: { "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com" }

Output: { "id": "58097cd698686961f6fada50","lastName": "Jobs","middleName": "Nick", "email": "jno@mac.com","firstName": "Steve"}
```

##### GET /customerservice/customer/58097cd698686961f6fada50
```
Output: { "id": "58097cd698686961f6fada50","lastName": "Jobs","middleName": "Nick", "email": "jno@mac.com","firstName": "Steve"}
```

## Partner

##### POST /partnerservice/partner

```
Payload: { "lastName":"Jobs","middleName":"Paul","firstName":"Steve","email":"spj@mac.com", "company":"Apple, Inc.","homepage":"www.apple.com" }

Output: {
  "id": "5809894b9868aa2b76ef10d4",
  "lastName": "Jobs",
  "middleName": "Paul",
  "email": "spj@mac.com",
  "firstName": "Steve",
  "company": "Apple, Inc.",
  "homepage": "www.apple.com"
}
```

##### GET /partnerservice/partner/5809894b9868aa2b76ef10d4

```
Output: {
  "id": "5809894b9868aa2b76ef10d4",
  "lastName": "Jobs",
  "middleName": "Paul",
  "email": "spj@mac.com",
  "firstName": "Steve",
  "company": "Apple, Inc.",
  "homepage": "www.apple.com"
}
```

## Product

##### POST /productservice/product

```
Payload:  { "name":"Macbook Pro","description":"A Laptop","cost":"2000","invein":"5", "curcode":"USD", "partnerid":"5809804f9868df2341045b20" }

Output: {
  "name": "Macbook Pro",
  "description": "A Laptop",
  "cost": "2000",
  "invein": "5",
  "curcode": "USD",
  "partner": "Apple, Inc.",
  "id": "580987a99868aa2b76ef10d3"
}

```

##### GET /productservice/product/580987a99868aa2b76ef10d3

```
Output: {
  "name": "Macbook Pro",
  "description": "A Laptop",
  "cost": "2000",
  "invein": "5",
  "curcode": "USD",
  "partner": "Apple, Inc.",
  "id": "580987a99868aa2b76ef10d3"
}
```

##### POST /productservice/search

```
Payload: { "searchterm":"iPhone"}

Output: {
  "searchterm": "iPhone",
  "results": [
    {
      "name": "iPhone 5s",
      "description": "This is the iPhone 5s",
      "cost": "10000",
      "invein": "5",
      "curcode": "USD",
      "partner": "Apple, Inc.",
      "id": "5809804f9868df2341045b21"
    },
    {
      "name": "iPhone 6s",
      "description": "This is the iPhone 6s",
      "cost": "10000",
      "invein": "5",
      "curcode": "USD",
      "partner": "Apple, Inc.",
      "id": "5809804f9868df2341045b22"
    },
    {
      "name": "iPhone 7 plus",
      "description": "This is the iPhone 7 plus",
      "cost": "10000",
      "invein": "5",
      "curcode": "USD",
      "partner": "Apple, Inc.",
      "id": "5809804f9868df2341045b23"
    }
  ]
}
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










