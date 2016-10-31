# Web Services Project (Fall 2016) 

John O'Sullivan

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

Hosted: <a>https://fall2016wsp.herokuapp.com/</a>

Note: This is running on a free dyno thus it is unactive. Requests will initially take longer to allow server to boot up.

## Customer

###### Customer Object 
The customer object has the following api calls to which they will allow you to create, edit, delete the customer object and the support object's supporting data object like phone and address.

* POST /api/customerservice/customer 

```
Payload: { "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com", "propic":"http://www.google.com/somepic" }
Response: {
  "id": "5817584ae4b0cccc9183401a",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "propic": "http://www.google.com/somepic",
  "addresses": null,
  "phones": null
}
```

* PUT /api/customerservice/customer

```
Payload: { "id": "5817584ae4b0cccc9183401a", "lastName":"O'Sullivan","middleName":"Nick","firstName":"Steve","email":"jno@mac.com", "propic":"http://www.google.com/somepic" }
Response: {
  "id": "5817584ae4b0cccc9183401a",
  "lastName": "O'Sullivan",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "propic": "http://www.google.com/somepic",
  "addresses": null,
  "phones": null
}
```

* GET /api/customerservice/customer/{id}

```
Call: /api/customerservice/customer/5817584ae4b0cccc9183401a
Response: {
  "id": "5817584ae4b0cccc9183401a",
  "lastName": "O'Sullivan",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "propic": "http://www.google.com/somepic",
  "addresses": null,
  "phones": null
}
```

* DELETE /api/customerservice/customer/{id}

```
Call: /api/customerservice/customer/5817584ae4b0cccc9183401a
Response: 
```
###### Customer's Phone Object


* POST /api/customerservice/customer/phone

```
Payload: { "type":"Cell","phone":"1-847-256-7071","user":"58175932e4b0cccc9183401b" }
Response: {
  "phone": "1-847-256-7071",
  "type": "Cell",
  "id": "58175989e4b0cccc9183401c",
  "user": "58175932e4b0cccc9183401b"
}
```

* PUT /api/customerservice/customer/phone

```
Payload: { "id": "58175989e4b0cccc9183401c", "type":"Home","phone":"1-847-256-7071","user":"58175932e4b0cccc9183401b" }
Response: {
  "phone": "1-847-256-7071",
  "type": "Home",
  "id": "58175989e4b0cccc9183401c",
  "user": "58175932e4b0cccc9183401b"
}
```

* GET /api/customerservice/customer/{customerid}

```
Call: /api/customerservice/customer/58175932e4b0cccc9183401b
Response: {
  "id": "58175932e4b0cccc9183401b",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "propic": "http://www.google.com/somepic",
  "addresses": [
  ],
  "phones": [
    {
      "phone": "1-847-256-7071",
      "type": "Home",
      "id": "58175989e4b0cccc9183401c",
      "user": "58175932e4b0cccc9183401b"
    }
  ]
}
```

* DELETE /api/customerservice/customer/phone/{phoneid}

```
Call: /api/customerservice/customer/phone/58175989e4b0cccc9183401c
Respsonse: 200 Status Code
```

###### Customer's Address Object

* POST /api/customerservice/customer/address

```
Payload: { "address":"700 Ouilmette Ln","city":"Wilmette","state":"WI","user":"","zip":60091, "user":"58175932e4b0cccc9183401b" }
Response: {
  "address": "700 Ouilmette Ln",
  "city": "Wilmette",
  "state": "WI",
  "id": "58175a88e4b0cccc9183401d",
  "user": "58175932e4b0cccc9183401b",
  "zip": "60091"
}
```

* GET /api/customerservice/customer/{customerid}

```
Call: /api/customerservice/customer/58175932e4b0cccc9183401b
Response: {
  "id": "58175932e4b0cccc9183401b",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "propic": "http://www.google.com/somepic",
  "addresses": [
    {
      "address": "700 Ouilmette Ln",
      "city": "Wilmette",
      "state": "WI",
      "id": "58175a88e4b0cccc9183401d",
      "user": "58175932e4b0cccc9183401b",
      "zip": "60091"
    }
  ],
  "phones": [
  ]
}
```

* DELETE /api/customerservice/customer/address/{addressid}

```
Call: /api/customerservice/customer/address/58175a88e4b0cccc9183401d
Response: 200 Status Code
```


## Partner

#### Partner Object

The partner object has the following api calls to which they will allow you to create, edit, delete the partner object and the support object's supporting data object like phone and address.

* POST /api/customerservice/partner

```
Payload: { "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com","homepage":"http://www.apple.com", "company":"Apple, Inc." }
Response: {
  "id": "58175ba4e4b0cccc9183401e",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jno@mac.com",
  "firstName": "Steve",
  "company": "Apple, Inc.",
  "homepage": "http://www.apple.com",
  "addresses": null,
  "phones": null
}
```

* PUT /api/customerservice/partner

```
Payload: { "id": "58175ba4e4b0cccc9183401e", "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com","homepage":"http://www.apple.com", "company":"Apple, Inc." }
Response: {
  "id": "58175ba4e4b0cccc9183401e",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jimmy@mac.com",
  "firstName": "Jimmy",
  "company": "Apple, Inc.",
  "homepage": "http://www.iphone.com",
  "addresses": null,
  "phones": null
}
```

* GET /api/customerservice/partner/{partnerid}

```
Call: /api/partnerservice/partner/58175ba4e4b0cccc9183401e
Response: {
  "id": "58175ba4e4b0cccc9183401e",
  "lastName": "Jobs",
  "middleName": "Nick",
  "email": "jimmy@mac.com",
  "firstName": "Jimmy",
  "company": "Apple, Inc.",
  "homepage": "http://www.apple.com",
  "addresses": [
  ],
  "phones": [
  ]
}
```

* DELETE /api/customerservice/partner/{partnerid}

```
Call: /api/partnerservice/partner/58175ba4e4b0cccc9183401e
Respsonse: 200 Status Code
```

###### Partner's Phone Object and Address Object

The phone and address supporting object for partner follow the same design pattern as the customer. Please refer to the customer example with the follow chanages,

"/customerservice/customer" changes to "/partnerservice/partner"

"customerid" changes to "partnerid"

###### Partner's Address Object



## Product

###### Product Object

* POST /api/productservice/product

```
```

* GET /api/productservice/product/{id}

```
```

###### Product Search

* POST /api/productservice/search

```
```

## Order

* POST /api/orderservice/order

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










