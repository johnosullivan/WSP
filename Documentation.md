# Documentation

--------

All java files have inline comments included for additional help. 
Javadocs located at the root of this repository.

# Architecture

The core of the structure is a database object (Database.java). This file creates the database entries as well finds data objects in the database to sterilize objects such as Customer, Partner, Product, Order, Payment, and Reviews. The patterns used accross the structure are a default constructor and id constructor. The default constructor, when called, will create a blank object, like a customer. The object set methods can be used to build the object and create it in the database. Instead of calling the default constructor or calling the constructor with id, the object calls an instance of the database helper and pulls the data via an mongo query. The returned mongo object is parsed and the object is sterilized and ready for manipulation. This is useful for reviewing orders and creating or editing any of the objects. If a product is pulled once the object is created, all support links such as partner object, inventory, and the product's own metadata are ready for transversing through and editing. If a customer is created and does not have a payment saved to the profile, a blank object is already created for ease of use. All the system has to do is call the customer getPayment to return the object, set parameters and save. With this structure, once it comes time to add the service layer, the objects will be easy to manipulate to fulfill the request needed. 