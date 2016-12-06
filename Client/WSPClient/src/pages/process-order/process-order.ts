import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { CartPage } from '../cart/cart'
import { MyOrdersPage } from '../my-orders/my-orders'
import { MyData } from "../../providers/my-data";

/*
  Generated class for the ProcessOrder page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-process-order',
  templateUrl: 'process-order.html'
})
export class ProcessOrderPage {

  data:any;
  http: Http
  md: MyData;
  constructor(public navCtrl: NavController,public navParams: NavParams,h: Http, m:MyData) {
    this.data = navParams.get('data');
    this.http = h;
    var str = this.data._body;

    this.data = JSON.parse(str);
    this.md = m;
    console.log(this.data);

  }

  goCart() {
    this.navCtrl.setRoot(CartPage)
  }

  goOrders() {
    this.md.clearcart();
    this.navCtrl.setRoot(MyOrdersPage)
  }

  linkexcute(link) {
    console.log(link);
    if (link['action'] == 'Return To Cart') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var p = JSON.parse(link.payload);
      console.log(p);
      this.http.put(link.uri,p,{ headers: headers }).subscribe( response => this.goCart());
    }
    if (link['action'] == 'Process') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var p = JSON.parse(link.payload);
      console.log(p);
      this.http.put(link.uri,p,{ headers: headers }).subscribe( response => this.goOrders());
    }

  }

  ionViewDidLoad() {  }

}
