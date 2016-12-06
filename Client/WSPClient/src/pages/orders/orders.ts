import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";
/*
  Generated class for the Orders page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-orders',
  templateUrl: 'orders.html'
})
export class OrdersPage {
  http: Http;
  nav: NavController;
  prodata: MyData;
  data: any;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController,h: Http, d:MyData) {
    this.http = h;
    this.nav = navCtrl;
    this.prodata = d;
    this.data = [];
  }
  parse(d) {
    this.data = JSON.parse(d._body)['orders'].reverse();
    console.log(this.data)
  }
  refresh() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.get(this.url + '/orderservice/orders/partner/' +  this.prodata.getObject()['id'], { headers: headers }).subscribe( response => this.parse(response));

  }
  linkexcute(link) {
    if (link['method'] == 'PUT') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var p = JSON.parse(link.payload);
      this.http.put(link.uri,p,{ headers: headers }).subscribe( response => this.refresh());
    }
  }

  ionViewDidLoad() {
    this.refresh();
  }

}
