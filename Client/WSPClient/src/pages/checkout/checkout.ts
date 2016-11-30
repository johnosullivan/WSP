import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';

@Component({
  selector: 'page-checkout',
  templateUrl: 'checkout.html'
})
export class CheckoutPage {
  data : MyData;
  address: any;
  nav : NavController;
  http: Http;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, data : MyData, h:Http) {
    this.data = data;
    this.address = '';
    this.nav = navCtrl;
    this.http = h;
  }
  ionViewDidLoad() {  }
  orderprocessed(data) {
    this.data.clearcart();
    this.nav.pop();
  }
  process() {
    var payload = {};
    var userid = this.data.getObject()['id'];
    payload['address'] = this.address;
    payload['customer'] = userid;
    var itemarray = [];
    var cart = this.data.getCart();
    for (var i = 0; i < cart.length; i++) {
      var temp = {};
      temp['q'] = cart[i]['amount'];
      temp['productid'] = cart[i]['id'];
      itemarray.push(temp);
    }
    payload['items'] = itemarray;
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/orderservice/order', payload, { headers: headers }).subscribe( response => this.orderprocessed(response));
  }
}
