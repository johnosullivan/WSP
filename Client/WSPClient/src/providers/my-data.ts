import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the MyData provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class MyData {

  data: string;
  isLogin: any;
  object: any;
  cart: any;
  addresses: any;
  phones: any;
  htto: any;
  type: any;
  //url: string = 'https://fall2016wsp.herokuapp.com/api';
  url: string = 'http://localhost:8080/WSP/api';
  constructor(public http: Http) {
    this.data = '';
    this.isLogin = false;
    this.object = {};
    this.cart = [];
    this.addresses = [];
    this.phones = [];
    this.http = http;
    this.type = 0;
  }

  addAddress(v) {
    this.addresses.push(v);
  }

  getAddresses() {
  	return this.addresses;
  }

  setAddCart(v) {
  	this.cart.push(v);
  }

  getCart() {
  	return this.cart;
  }

  setLogin(v) {
  	this.isLogin = v;
  }

  getLogin() {
  	return this.isLogin;
  }

  processprofilec(v) {
    var json = JSON.parse(v._body);
    var addressarray = json['addresses'];
    for (var i = 0; i < addressarray.length; i++) {
      this.addresses.push(addressarray[i]);
    }

    var phonearray = json['phones'];
    for (var i = 0; i < phonearray.length; i++) {
      this.phones.push(phonearray[i]);
    }
    console.log(phonearray);
    console.log(addressarray);
  }

  addPhones(v) {
    this.phones.push(v);
  }

  getPhones() {
    return this.phones;
  }

  getType() {
    return this.type;
  }

  processprofilep(v) {
    var json = JSON.parse(v._body);
    var addressarray = json['addresses'];
    for (var i = 0; i < addressarray.length; i++) {
      this.addresses.push(addressarray[i]);
    }

    var phonearray = json['phones'];
    for (var i = 0; i < phonearray.length; i++) {
      this.phones.push(phonearray[i]);
    }
    console.log(phonearray);
    console.log(addressarray);
  }

  cartitemI(item) {
    for (var i = 0; i < this.cart.length; i++) {
      var pro = this.cart[i];
      if (pro['id'] == item['id']) {
        pro['amount']++;
      }
    }
  }
  cartitemD(item) {
    for (var i = 0; i < this.cart.length; i++) {
      var pro = this.cart[i];
      if (pro['id'] == item['id']) {
        if (pro['amount'] != 1) {
          pro['amount']--;
        } else {
          this.cart.splice(i,1);
        }
      }
    }
  }

  removeCartItem(item) {
    for (var i = 0; i < this.cart.length; i++) {
      var pro = this.cart[i];
      if (pro['id'] == item['id']) {
        this.cart.splice(i,1);
      }
    }
  }

  clearcart() {
    this.cart = [];
  }

  update(v) {

    /*this.object['first'] = v['firstname'];
    this.object['last'] = v['lastname'];
    this.object['middle'] = v['middlename'];
    this.object['email'] = v['email'];
    if (this.type == 1) {
      this.object['company'] = v['company'];
      this.object['homepage'] = v['homepage'];
    }*/
  }

  setObject(v) {
    var t = v['type'];
    var userid = v['id'];
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    if (t == 0) {
      this.http.get(this.url + '/customerservice/customer/' + userid, { headers: headers }).subscribe(
      		response => this.processprofilec(response)
			);
    }
    if (t == 1) {
      this.http.get(this.url + '/partnerservice/partner/' + userid, { headers: headers }).subscribe(
          response => this.processprofilep(response)
      );
    }
  	this.object = v;
  }

  logout() {
    this.object = {};
    this.addresses = [];
    this.phones = [];
    this.isLogin = false;
  }

  getObject() {
  	return this.object;
  }

  setData(d) {
  	this.data = d;
  }

  getData() {
  	return this.data;
  }

}
