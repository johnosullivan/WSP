# Web Services Project (Fall 2016) John O'Sullivan 

----
----

For the project, I decided to go the no-sql way. (MongoDB)

1) Install MongoDB at https://docs.mongodb.com/manual/installation/

2) Once installed, I launched the monogd server.

3) Run the AppTest.java as a JUnit Test.

<b>Supporting Libraries Needed For Java Project (Web Services)</b>

- bson-2.13.3.jar
- hamcrest-core-1.3.jar
- junit-4.12.jar
- mongo-java-driver-2.13.3.jar
- Apache CXF Library [3.1.7]
- Apache Tomcat 9.0
- JRE System Library [Java SE 8 [1.8.0_101]]

<b>Supporting Libraries Needed For Client Project (Client)</b>

- npm
- node.js
- ionic
- Cordova
- TypeScript
- Xcode (For iOS Client Only)

----
----

# Client + Server(HATEOAS) 

### Pre-Requisites Install

<b>ionic (v2): <a href="http://ionicframework.com/docs/guide/installation.html">http://ionicframework.com/docs/guide/installation.html</a></b>

<b>TypeScript (Current): <a href="https://www.typescriptlang.org/#download-links">https://www.typescriptlang.org/#download-links</a></b>

Client wrote <a href="https://www.typescriptlang.org/">TypeScript</a> with the use of <a href="https://ionic.io/">Cordova / Ionic</a>.

The Client is a web application which with the support of the Cordova and Ionic framework can be built to run on the iOS, Android, and Web Browsers.

The HATEOAS diagram can be found: <a href="">Here</a>

To run the client make sure your broswer has the cross orgin feature of its security <b>DISABLE</b>.
<b>If you do not enable cross-orgin the client for the web browser it will not work at all. The cross orgin enable the browser to call the web service to process your requests. </b> 

To run navigate to the client folder and run the following:

<b>Build Client:</b>

```
ionic build
```

<b>For Web Application:</b>

```
ionic serve
```

<b>For iOS Devices (<a href="https://developer.apple.com/xcode/">Xcode</a> Required)</b>

```
ionic run ios
```

----
----

# API ApacheCXF/Tomcat 

### <b>Note: This is running on a free dyno (Heroku). Requests will initially take longer to allow the server to boot up. If you are running from eclipse, please check the client and DAL configs to ensure proper use, <a href="https://github.com/johnosullivan/WSP/blob/master/src/client/jacksonClient/ClientConfig.java">ClientConfig</a> and <a href="https://github.com/johnosullivan/WSP/blob/master/src/dal/Configs.java">Configs</a>. The requests below are examples with mock data. Make sure to have valid IDs or you will get a 400 status code.</b> 

Hosted: <a href="https://fall2016wsp.herokuapp.com/">https://fall2016wsp.herokuapp.com/</a>

Deployment CLI: <a href="https://github.com/heroku/heroku-cli-deploy">https://github.com/heroku/heroku-cli-deploy</a>

Jackson Test <a href="https://github.com/johnosullivan/WSP/tree/master/src/client/jacksonClient">Here</a>

The following REST calls for the documentation were made with a Mac app called CocoaRestClient which can be downloaded and installed <a href="http://mmattozzi.github.io/cocoa-rest-client/">Here</a>. If one chooses to run in eclipse I would <b>strongly recommended</b> this to build payloads and view responds without having to filter through the server output in eclipse. The ClientTestLogs.txt is the ouput from the Postman test. There are four situations, two are creating,editing,deleting data around the partner and customer objects. One is about creating,editing,deleting products and searching for then. The last Postman test will do a full order and test what the customer and partner see after an order created. For more details please review the API documentation.

### Content-Type Allowed:  "application/xml", "application/json"
### Accept Allowed:  "application/xml", "application/json"

# Service Documentation

----
----

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/customer/service/CustomerResource.java">Customer Service</a>

###### Customer Object 
The customer object has the following api calls to which they will allow you to create, edit, delete the customer object and the support object's supporting data objects like phone and address.

Required in request: firstName,lastName,email

* POST "/api/customerservice/customer" Creates a new customer from the payload.

```
Payload: { "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com", "propic":"http://www.google.com/somepic" }
Response:
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <id>582365b5e4b0e98526a7facb</id>
    <lastName>Jobs</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <propic>http://www.google.com/somepic</propic>
</customer>
```

* PUT "/api/customerservice/customer" Updates the customer from the payload. Make sure to include the ID or the request will fail with 400.

```
Payload: { "id": "582365b5e4b0e98526a7facb", "lastName":"O'Sullivan","middleName":"Nick","firstName":"Steve","email":"jno@mac.com", "propic":"http://www.google.com/somepic" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <id>582365b5e4b0e98526a7facb</id>
    <lastName>O'Sullivan</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <propic>http://www.google.com/somepic</propic>
</customer>
```

* GET "/api/customerservice/customer/{customerid}" Gets the customer with the customerid from url.

```
Call: /api/customerservice/customer/5817584ae4b0cccc9183401a
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <id>582365b5e4b0e98526a7facb</id>
    <lastName>O'Sullivan</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <propic>http://www.google.com/somepic</propic>
</customer>
```

* DELETE "/api/customerservice/customer/{customerid}" Delete the customer from database.

```
Call: /api/customerservice/customer/582365b5e4b0e98526a7facb
Response: 200 Status Code
```
###### Customer's Phone Object


* POST "/api/customerservice/customer/phone" Creates a new phone for customer from the payload.

```
Payload: { "type":"Cell","phone":"1-847-256-7071","user":"582365b5e4b0e98526a7facb" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<phone>
    <phone>1-847-256-7071</phone>
    <type>Cell</type>
    <id>5823662de4b0e98526a7facc</id>
    <user>582365b5e4b0e98526a7facb</user>
</phone>
```

* PUT "/api/customerservice/customer/phone" Updates the phone of customer from the payload.

```
Payload: { "id": "5823662de4b0e98526a7facc", "type":"Home","phone":"1-847-256-7071" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<phone>
    <phone>1-847-256-7071</phone>
    <type>Home</type>
    <id>5823662de4b0e98526a7facc</id>
</phone>
```

* GET "/api/customerservice/customer/{customerid}" Gets the customer from customerid in url to shows the phones in response.


```
Call: /api/customerservice/customer/58175932e4b0cccc9183401b
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <id>582365b5e4b0e98526a7facb</id>
    <lastName>O'Sullivan</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <propic>http://www.google.com/somepic</propic>
    <phones>
        <phone>1-847-256-7071</phone>
        <type>Home</type>
        <id>5823662de4b0e98526a7facc</id>
        <user>582365b5e4b0e98526a7facb</user>
    </phones>
</customer>
```

* DELETE "/api/customerservice/customer/phone/{phoneid}" Delete an phone number with the phoneid.

```
Call: /api/customerservice/customer/phone/5823662de4b0e98526a7facc
Respsonse: 200 Status Code
```

###### Customer's Address Object

* POST "/api/customerservice/customer/address" Creates a new address for the customer from the payload.

```
Payload: { "address":"700 Ouilmette Ln","city":"Wilmette","state":"WI","user":"","zip":60091, "user":"582365b5e4b0e98526a7facb" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<address>
    <address>700 Ouilmette Ln</address>
    <city>Wilmette</city>
    <state>WI</state>
    <id>582366f2e4b0e98526a7facd</id>
    <user>582365b5e4b0e98526a7facb</user>
    <zip>60091</zip>
</address>
```

* GET "/api/customerservice/customer/{customerid}" Gets the customer from customerid in url to shows the addresses in response.

```
Call: /api/customerservice/customer/582365b5e4b0e98526a7facb
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <id>582365b5e4b0e98526a7facb</id>
    <lastName>O'Sullivan</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <propic>http://www.google.com/somepic</propic>
    <addresses>
        <address>700 Ouilmette Ln</address>
        <city>Wilmette</city>
        <state>WI</state>
        <id>582366f2e4b0e98526a7facd</id>
        <user>582365b5e4b0e98526a7facb</user>
        <zip>60091</zip>
    </addresses>
</customer>
```

* DELETE "/api/customerservice/customer/address/{addressid}" Delete the customer's address from the database.

```
Call: /api/customerservice/customer/address/582365b5e4b0e98526a7facb
Response: 200 Status Code
```

----
----
## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/partner/service/PartnerResource.java">Partner Service</a>

#### Partner Object

The partner object has the following api calls to which they will allow you to create, edit, delete the partner object and the support objects supporting data object like phone and address.

Required in request: firstName,lastName,email

* POST "/api/partnerservice/partner" Creates a new partner from the payload.

```
Payload: { "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com","homepage":"http://www.apple.com", "company":"Apple, Inc." }
Response:
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<partner>
    <id>58236741e4b0e98526a7face</id>
    <lastName>Jobs</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <company>Apple, Inc.</company>
    <homepage>http://www.apple.com</homepage>
</partner>
```

* PUT "/api/partnerservice/partner" Takes the payload and uses the ID to update the partner information.

```
Payload: { "id": "58236741e4b0e98526a7face", "lastName":"Jobs","middleName":"Nick","firstName":"Steve","email":"jno@mac.com","homepage":"http://www.apple.com", "company":"Apple, Inc." }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<partner>
    <id>58236741e4b0e98526a7face</id>
    <lastName>Jobs</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <company>Apple, Inc.</company>
    <homepage>http://www.apple.com</homepage>
</partner>
```

* GET "/api/partnerservice/partner/{partnerid}" Gets the partner info from the ID in the url.

```
Call: /api/partnerservice/partner/58236741e4b0e98526a7face
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<partner>
    <id>58236741e4b0e98526a7face</id>
    <lastName>Jobs</lastName>
    <middleName>Nick</middleName>
    <email>jno@mac.com</email>
    <firstName>Steve</firstName>
    <company>Apple, Inc.</company>
    <homepage>http://www.apple.com</homepage>
</partner>
```

* DELETE "/api/partnerservice/partner/{partnerid}" Delete the partner from the database.

```
Call: /api/partnerservice/partner/58236741e4b0e98526a7face
Respsonse: 200 Status Code
```

###### Partner's Phone Object and Address Object

The phone and address supporting object for partner follow the same design pattern as the customer. Please refer to the customer example with the follow chanages,

"/customerservice/customer" changes to "/partnerservice/partner"

"customerid" changes to "partnerid"

----
----
## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/payment/service/PaymentResource.java">Payment Service</a>

* POST "/api/paymentservice/payment/customer" OR "/api/paymentservice/payment/partner" This will create a new payment for the customer or partner. Be sure to include the user's ID or an 400 status code will return.

```
Payload: { "type":"CC","user":"582365b5e4b0e98526a7facb", "ccnum":"1234432112344321", "ccexp":"01/12", "ccsec":"123", "billing":"582366f2e4b0e98526a7facd" }
Response: 200 Status Code 
```

* PUT "/api/paymentservice/payment/customer" OR "/api/paymentservice/payment/partner" This will take the payload an update the payment information in file, be sure to include the user's ID or an 400 status code will return.

```
Payload: { "type":"CC","user":"582365b5e4b0e98526a7facb", "ccnum":"0000000000000000", "ccexp":"01/12", "ccsec":"123", "billing":"582366f2e4b0e98526a7facd" }
Response: 200 Status Code
```

* GET "/api/paymentservice/payment/customer/{customerid}" OR "/api/paymentservice/payment/partner/{partnerid}" This gets the customer's or partner's payment information. 

```
Call: /api/paymentservice/payment/customer/582365b5e4b0e98526a7facb
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<payment>
    <billing>700 Ouilmette Ln Wilmette WI 60091</billing>
    <ccnum>1234432112344321</ccnum>
    <ccexp>01/12</ccexp>
    <ccsec>123</ccsec>
</payment>
```

* DELETE "/api/paymentservice/payment/customer/{customerid}" OR "/api/paymentservice/payment/partner/{partnerid}" Will remove the customer's or partner's payment information from the server.

```
Call: /api/paymentservice/payment/customer/582365b5e4b0e98526a7facb
Response: 200 Status Code
```


----
----
## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/product/service/ProductResource.java">Product Service</a>

###### Product Object

* POST "/api/productservice/product" This will take the payload and create a new product and return the product with a unique ID.

Required in request: All parameters

```
Payload: { "name":"iPhone 7", "description":"This is a cool iphone", "cost":750, "curcode":"US", "invein":10, "partnerid":"58236741e4b0e98526a7face" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<product>
    <name>iPhone 7</name>
    <description>This is a cool iphone</description>
    <cost>750</cost>
    <invein>10</invein>
    <curcode>US</curcode>
    <partnerid>Apple, Inc.</partnerid>
    <id>58236a9ae4b0e98526a7fad2</id>
</product>
```

* PUT "/api/productservice/product" Will update the product info with payload, but requires the productid in the payload to allow the server to update the correct product. Once a product is create the partner who owns it cannot change. Only the information about the product can change like name, description, and inventory.

```
Payload: { "name":"iPhone 6 Plus", "description":"This is a cool iphone", "cost":650, "curcode":"US", "invein":20, "id":"58236a9ae4b0e98526a7fad2" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<product>
    <name>iPhone 6 Plus</name>
    <description>This is a cool iphone</description>
    <cost>650</cost>
    <invein>20</invein>
    <curcode>US</curcode>
    <id>58236a9ae4b0e98526a7fad2</id>
</product>
```

* GET "/api/productservice/product/{productid}" Will get the product with the productid in url.

```
Call: /api/productservice/product/58236a9ae4b0e98526a7fad2
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<product>
    <name>iPhone 6 Plus</name>
    <description>This is a cool iphone</description>
    <cost>650</cost>
    <invein>20</invein>
    <curcode>US</curcode>
    <partnerid>Apple, Inc.</partnerid>
    <id>58236a9ae4b0e98526a7fad2</id>
</product>
```
* DELETE "/api/productservice/product/{productid}" Will delete the product with the given productid.

```
Call: /api/productservice/product/58236a9ae4b0e98526a7fad2
Response: 200 Status Code
```

###### Product Search

* POST "/api/productservice/search" Will use the payload to search the product table for product related to serach term and return an array of results.

```
Payload: { "searchterm":"iPhone"}
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<search>
    <searchterm>iPhone</searchterm>
    <results>
        <name>iPhone 7</name>
        <description>This is a cool iphone</description>
        <cost>750</cost>
        <invein>10</invein>
        <curcode>US</curcode>
        <partnerid>Apple, Inc.</partnerid>
        <id>5817a0b5e4b0a35a32e331ad</id>
    </results>
    <results>
        <name>iPhone 6 Plus</name>
        <description>This is a cool iphone</description>
        <cost>650</cost>
        <invein>20</invein>
        <curcode>US</curcode>
        <partnerid>Apple, Inc.</partnerid>
        <id>58236a9ae4b0e98526a7fad2</id>
    </results>
</search>
```
----
----

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/order/service/OrderResource.java">Order Service</a>

* POST "/api/orderservice/order" Will create an order with the payload. This payload requires a valid address and customer ID. The items are an JSON array with an valid product ID and the number ordered.

```
Payload: { "items": [ {"q":2,"productid":"58236a9ae4b0e98526a7fad2"} ], "address":"582366f2e4b0e98526a7facd", "customer":"582365b5e4b0e98526a7facb" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order>
    <items>
        <q>2</q>
        <productid>58236a9ae4b0e98526a7fad2</productid>
    </items>
    <address>582366f2e4b0e98526a7facd</address>
    <customer>582365b5e4b0e98526a7facb</customer>
    <comfirm>754754667396675466739739</comfirm>
    <status>PROCESSED</status>
    <id>58236deae4b0e98526a7fad9</id>
    <total>0</total>
</order>
```

* GET "/api/orderservice/order/{orderid}" Will get the order with the orderid.

```
Call: /api/orderservice/order/58236deae4b0e98526a7fad9
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order>
    <items>
        <q>2</q>
        <productid>58236a9ae4b0e98526a7fad2</productid>
    </items>
    <address>582366f2e4b0e98526a7facd</address>
    <customer>Steve Nick O'Sullivan</customer>
    <comfirm>754754667396675466739739</comfirm>
    <status>PROCESSED</status>
    <id>58236deae4b0e98526a7fad9</id>
    <total>1300</total>
</order>
```

###### Get Customer Orders

GET "/api/orderservice/orders/customer/{customerid}" This will get all the orders the customer has made and cancelled.

```
Call: /api/orderservice/orders/customer/582365b5e4b0e98526a7facb
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<orders>
    <orders>
        <items>
            <q>2</q>
            <productid>58236a9ae4b0e98526a7fad2</productid>
        </items>
        <address>700 Ouilmette Ln Wilmette WI 60091</address>
        <customer>Steve Nick O'Sullivan</customer>
        <comfirm>754754667396675466739739</comfirm>
        <status>PROCESSED</status>
        <id>582365b5e4b0e98526a7facb</id>
        <total>1300</total>
    </orders>
</orders>
```

###### Status Code:
1 => Delivered

* PUT (Delivered Order) "/api/orderservice/order/customer" Will change the status of the order for the customer to delivered. This will allow the customer to write a review on the product in said order.

```
Payload: { "orderid":"58236deae4b0e98526a7fad9", "code":1 }
Response: 200 Status Code
```

###### Get Partners Orders

* GET "/api/orderservice/orders/partner/{partnerid}" Will get all the order that relate to the products the partner has in the database.

```
Call: /api/orderservice/orders/partner/58236741e4b0e98526a7face
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<orders>
    <orders>
        <shipping>700 Ouilmette Ln Wilmette WI 60091</shipping>
        <orderid>58236deae4b0e98526a7fad9</orderid>
        <orderstatus>PROCESSED</orderstatus>
        <items>
            <q>2</q>
            <productid>58236a9ae4b0e98526a7fad2</productid>
        </items>
    </orders>
</orders>
```

* PUT (Shipped Order) "/api/orderservice/order/partner" Will change the status of the order viewed to shipped.

###### Status Code:
1 => Shipped

```
Payload: { "orderid":"58236deae4b0e98526a7fad9", "code":1 }
Response: 200 Status Code
```

###### Simple Status Check

* GET "/api/orderservice/order/status/{orderid}" Will get the order from the orderid and return just the status.

```
Call: /api/orderservice/order/status/582365b5e4b0e98526a7facb
Respsone:
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order>
    <status>DELIVERED</status>
</order>
```

###### Cancel Order

* DELETE "/api/orderservice/order/{orderid}" Will cancel the order with the orderid

```
Call: /api/orderservice/order/581774aee4b0cccc91834032
Respsone: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order>
    <items>
        <q>2</q>
        <productid>58236a9ae4b0e98526a7fad2</productid>
    </items>
    <address>582366f2e4b0e98526a7facd</address>
    <customer>Steve Nick O'Sullivan</customer>
    <comfirm>754754667396675466739739</comfirm>
    <status>CANCELED</status>
    <id>58236deae4b0e98526a7fad9</id>
    <total>1300</total>
</order>
```
----
----

## <a href="https://github.com/johnosullivan/WSP/blob/master/src/service/review/service/ReviewResource.java">Review Service</a>

The review will only work if you have purchase a product and the status of the order is delivered.

* POST "/api/reviewservice/review" Posting to this address will create a new product review from the payload and return the review with its unique id. 

```
Payload { "order":"58236deae4b0e98526a7fad9", "review":"this is a good iphone", "stars":5, "product":"58236a9ae4b0e98526a7fad2" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<review>
    <id>5823741125ac10aaa5e3a915</id>
    <order>58236deae4b0e98526a7fad9</order>
    <review>this is a good iphone</review>
    <stars>5</stars>
    <product>58236a9ae4b0e98526a7fad2</product>
</review>
```

* PUT "/api/reviewservice/review" This will update the review with the new payload of information. Make sure to include the review id or the request will failed, due to not knowing what review to actually update.

```
Payload: { "id": "5823741125ac10aaa5e3a915", "order":"58236deae4b0e98526a7fad9", "review":"this is a great iphone", "stars":5, "product":"58236a9ae4b0e98526a7fad2" }
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<review>
    <id>5823741125ac10aaa5e3a915</id>
    <order>58236deae4b0e98526a7fad9</order>
    <review>this is a great iphone</review>
    <stars>5</stars>
    <product>58236a9ae4b0e98526a7fad2</product>
</review>
```

* GET "/api/reviewservice/review/{reviewid}" This will get the single review from the reviewid in the url.

```
Call: /api/reviewservice/review/5823741125ac10aaa5e3a915
Response: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<review>
    <id>5823741125ac10aaa5e3a915</id>
    <order>58236deae4b0e98526a7fad9</order>
    <review>this is a great iphone</review>
    <stars>5</stars>
    <product>58236a9ae4b0e98526a7fad2</product>
</review>
```

###### Get Reviews for a Product 

* Get "/api/reviewservice/reviews/product/{productid}" This request will gets all the reviews for the productid that is in the url.

```
Call: /api/reviewservice/reviews/product/58236a9ae4b0e98526a7fad2
Payload: 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<reviews>
    <reviews>
        <review>this is a great iphone</review>
        <stars>5</stars>
    </reviews>
</reviews>
```
----
----
# DAL/Model Documentation 

Features:

- Search in the database.
- Create edit/remove/update products, partners, and customers.
- Create orders and check status. Edit orders. 
- Accept order. Ship orders. 
- Track payment and create reports for products.
- Cancel order, refund, restock the product's inventory.
- Review products purchased by customers. 

For detailed documentation of our architecture and implementation. View <a href="https://github.com/johnosullivan/WSP/blob/master/Documentation.md">Here</a>. UML diagram <a href="https://github.com/johnosullivan/WSP/blob/master/diagram.png">Here</a>


----
----

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


----
----










