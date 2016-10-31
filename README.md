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

### <b>Note: This is running on a free dyno (Heroku). Requests will initially take longer to allow server to boot up. If you are running from eclipse please check the client and DAL configs to insure proper sure. <a href="https://github.com/johnosullivan/WSP/blob/master/src/client/jacksonClient/ClientConfig.java">ClientConfig</a> and <a href="https://github.com/johnosullivan/WSP/blob/master/src/dal/Configs.java">Configs</a> </b> 

Hosted: <a href="https://fall2016wsp.herokuapp.com/">https://fall2016wsp.herokuapp.com/</a>



## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/customer/service/CustomerResource.java">Customer Service</a>

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


## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/partner/service/PartnerResource.java">Partner Service</a>

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

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/payment/service/PaymentResource.java">Payment Service</a>

* POST /api/paymentservice/payment/customer OR /api/paymentservice/payment/partner

```
Payload: { "type":"CC","user":"58176fdee4b0cccc9183402d", "ccnum":"1234432112344321", "ccexp":"01/12", "ccsec":"123", "billing":"58177005e4b0cccc9183402e" }
Response: 200 Status Code 
```

* PUT /api/paymentservice/payment/customer OR /api/paymentservice/payment/partner

```
Payload: { "type":"CC","user":"58176fdee4b0cccc9183402d", "ccnum":"0000000000000000", "ccexp":"01/12", "ccsec":"123", "billing":"58177005e4b0cccc9183402e" }
Response: 200 Status Code
```

* GET /api/paymentservice/payment/customer/{customerid} OR /api/paymentservice/payment/partner/{partnerid}

```
Payload: { "type":"CC","user":"58176fdee4b0cccc9183402d", "ccnum":"0000000000000000", "ccexp":"01/12", "ccsec":"123", "billing":"58177005e4b0cccc9183402e" }
Response: {
  "billing": "723 Ouilmette Ln Wilmette IL 60091",
  "ccnum": "0000000000000000",
  "ccexp": "01/12",
  "ccsec": "123"
}
```

* DELETE /api/paymentservice/payment/customer/{customerid} OR /api/paymentservice/payment/partner/{partnerid}

```
Call: /api/paymentservice/payment/customer/58176fdee4b0cccc9183402d
Response: 200 Status Code
```



## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/product/service/ProductResource.java">Product Service</a>

###### Product Object

* POST /api/productservice/product

```
Payload: { "name":"iPhone 7", "description":"This is a cool iphone", "cost":750, "curcode":"US", "invein":10, "partnerid":"5817727ee4b0cccc91834030" }
Response: {
  "name": "iPhone 7",
  "description": "This is a cool iphone",
  "cost": "750",
  "invein": "10",
  "curcode": "US",
  "partnerid": "Apple, Inc.",
  "id": "58177308e4b0cccc91834031"
}
```

* PUT /api/productservice/product

```
Payload: { "name":"iPhone 6 Plus", "description":"This is a cool iphone", "cost":650, "curcode":"US", "invein":20, "id":"58177308e4b0cccc91834031" }
```

* GET /api/productservice/product/{id}

```
Call: /api/productservice/product/58177308e4b0cccc91834031
Response: {
  "name": "iPhone 6 Plus",
  "description": "This is a cool iphone",
  "cost": "650",
  "invein": "20",
  "curcode": "US",
  "partnerid": "Apple, Inc.",
  "id": "58177308e4b0cccc91834031"
}
```
* DELETE /api/productservice/product/{id}

```
Call: /api/productservice/product/58177308e4b0cccc91834031
Response: 200 Status Code
```

###### Product Search

* POST /api/productservice/search

```
Payload: { "searchterm":"iPhone"}
Response: {
  "searchterm": "iPhone",
  "results": [
    {
      "name": "iPhone 6 Plus",
      "description": "This is a cool iphone",
      "cost": "650",
      "invein": "20",
      "curcode": "US",
      "partnerid": "Apple, Inc.",
      "id": "58177308e4b0cccc91834031"
    }
  ]
}
```

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/order/service/OrderResource.java">Order Service</a>

* POST /api/orderservice/order

```
Payload: { "items": [ {"q":2,"productid":"58177308e4b0cccc91834031"} ], "address":"58177005e4b0cccc9183402e", "customer":"58176fdee4b0cccc9183402d" }
Response: {
  "items": [
    {
      "q": "2",
      "productid": "58177308e4b0cccc91834031"
    }
  ],
  "address": "58177005e4b0cccc9183402e",
  "customer": "58176fdee4b0cccc9183402d",
  "comfirm": "754754667396675466739739",
  "status": "PROCESSED",
  "id": "581774aee4b0cccc91834032",
  "total": "0"
}
```

* GET /api/orderservice/order/581774aee4b0cccc91834032

```
Call: /api/orderservice/order/{orderid}
Response: {
  "items": [
    {
      "q": "2",
      "productid": "58177308e4b0cccc91834031"
    }
  ],
  "address": "58177005e4b0cccc9183402e",
  "customer": "Steve Nick Jobs",
  "comfirm": "754754667396675466739739",
  "status": "PROCESSED",
  "id": "581774aee4b0cccc91834032",
  "total": "1300"
}
```

###### Get Customer Orders

GET /api/orderservice/orders/customer/{customerid}

```
Call: /api/orderservice/orders/customer/58176fdee4b0cccc9183402d
Response: {
  "orders": [
    {
      "items": [
        {
          "q": "2",
          "productid": "58177308e4b0cccc91834031"
        }
      ],
      "address": "723 Ouilmette Ln Wilmette IL 60091",
      "customer": "Steve Nick Jobs",
      "comfirm": "754754667396675466739739",
      "status": "PROCESSED",
      "id": "58176fdee4b0cccc9183402d",
      "total": "1300"
    }
  ]
}
```

###### Status Code:
1 => Delivered

* PUT (Delivered Order) /api/orderservice/order/customer

```
Payload: { "orderid":"581774aee4b0cccc91834032", "code":1 }
Response: 200 Status Code
```

###### Get Partners Orders

* GET /api/orderservice/orders/partner/{partnerid}

```
Call: /api/orderservice/orders/partner/5817727ee4b0cccc91834030
Response: {
  "orders": [
    {
      "shipping": "723 Ouilmette Ln Wilmette IL 60091",
      "orderid": "581774aee4b0cccc91834032",
      "orderstatus": "PROCESSED",
      "items": [
        {
          "q": "2",
          "productid": "58177308e4b0cccc91834031"
        }
      ]
    }
  ]
}
```

* PUT (Shipped Order) /api/orderservice/order/partner

###### Status Code:
1 => Shipped

```
Payload: { "orderid":"581774aee4b0cccc91834032", "code":1 }
Response: 200 Status Code
```

###### Simple Status Check

* GET /api/orderservice/order/status/{orderid}

```
Call: /api/orderservice/order/status/581774aee4b0cccc91834032
Respsone: {
  "status": "SHIPPED"
}
```

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/review/service/ReviewResource.java">Review Service</a>

The review will only work if you have purchase a product and the status of the order is delivered.

* POST /api/reviewservice/review

```
Payload { "order":"581774aee4b0cccc91834032", "review":"this is a good iphone", "stars":5, "product":"58177308e4b0cccc91834031" }
Response: {
  "id": "581779c7e4b06220b38227f3",
  "order": "581774aee4b0cccc91834032",
  "review": "this is a good iphone",
  "stars": "5",
  "product": "58177308e4b0cccc91834031"
}
```

* PUT /api/reviewservice/review

```
Payload: { "id": "581779c7e4b06220b38227f3", "order":"581774aee4b0cccc91834032", "review":"this is a great iphone", "stars":5, "product":"58177308e4b0cccc91834031" }
Response: {
  "id": "581779c7e4b06220b38227f3",
  "order": "581774aee4b0cccc91834032",
  "review": "this is a great iphone",
  "stars": "5",
  "product": "58177308e4b0cccc91834031"
}
```

* GET /api/reviewservice/review/{reviewid}

```
Call: /api/reviewservice/review/581779c7e4b06220b38227f3
Response: {
  "id": "581779c7e4b06220b38227f3",
  "order": "581774aee4b0cccc91834032",
  "review": "this is a great iphone",
  "stars": "5",
  "product": "58177308e4b0cccc91834031"
}
```

###### Get Reviews for a Product 

* Get /api/reviewservice/reviews/product/{productid}

```
Call: /api/reviewservice/reviews/product/58177308e4b0cccc91834031
Payload: {
  "reviews": [
    {
      "review": "this is a great iphone",
      "stars": "5"
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










