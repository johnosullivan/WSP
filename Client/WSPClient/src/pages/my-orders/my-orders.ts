import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";

@Component({
  selector: 'page-my-orders',
  templateUrl: 'my-orders.html'
})
export class MyOrdersPage {
  http : Http;
  orders : any;
  mydata : MyData;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, h:Http, d:MyData) {
    this.http = h;
    this.orders = [];
    this.mydata = d;
    this.loadOrders();
  }
  ionViewDidLoad() {  }
  process(data) {
    var object = JSON.parse(data._body);
    this.orders = object['orders'];
  }
  loadOrders() {
    var userid = this.mydata.getObject()['id'];
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.get(this.url + '/orderservice/orders/customer/' + userid, { headers: headers }).subscribe( response => this.process(response));
  }
}
