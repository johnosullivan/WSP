import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";
import { ViewOrderPage } from '../view-order/view-order';
import { ReviewOrderPage } from '../review-order/review-order';


@Component({
  selector: 'page-my-orders',
  templateUrl: 'my-orders.html'
})
export class MyOrdersPage {
  http : Http;
  orders : any;
  mydata : MyData;
  nav : NavController;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, h:Http, d:MyData) {
    this.http = h;
    this.orders = [];
    this.mydata = d;
    this.loadOrders();
    this.nav = navCtrl;
  }
  linkexcute(link,obj) {
    if (link['method'] == 'GET') {
      this.nav.push(ViewOrderPage, { order: obj});
    }
    if (link['method'] == 'DELETE') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.delete(link.uri, { headers: headers }).subscribe( response => this.loadOrders());
    }
    if (link['method'] == 'PUT') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var p = JSON.parse(link.payload);
      this.http.put(link.uri,p,{ headers: headers }).subscribe( response => this.loadOrders());
    }
    if (link['method'] == 'POST') {
      this.nav.push(ReviewOrderPage, { order: obj});
    }

  }
  ionViewDidLoad() {  }
  process(data) {
    var object = JSON.parse(data._body);
    this.orders = object['orders'].reverse();
    console.log(this.orders);
  }
  loadOrders() {
    var userid = this.mydata.getObject()['id'];
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.get(this.url + '/orderservice/orders/customer/' + userid, { headers: headers }).subscribe( response => this.process(response));
  }
}
