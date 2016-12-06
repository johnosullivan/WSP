import { Component } from '@angular/core';
import { MyData } from "../../providers/my-data";
import { NavController, NavParams } from 'ionic-angular';
import { Toast } from 'ionic-native';
import { Http , Headers } from '@angular/http';

@Component({
  selector: 'page-product',
  templateUrl: 'product.html'
})
export class Product {
  selectedItem: any;
  icons: string[];
  items: Array<{title: string, note: string, icon: string}>;
  title: any;
  json: any;
  prodata: MyData;
  count: any;
  nav : NavController;
  reviews : any;
  http : Http;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, public navParams: NavParams, pdata: MyData, h: Http) {
    this.selectedItem = navParams.get('item');
    this.title = '';
    this.http = h;
    this.nav = navCtrl;
    this.prodata = pdata;
    console.log(this.selectedItem._body);
    this.json = JSON.parse(this.selectedItem._body);
    console.log(this.json);
    this.title = this.json.name;
    this.count = 1;
    this.reviews = [];
  }
  parsereview(data) {
    var res = data._body;
   	var datas = JSON.parse(res);
    this.reviews = datas['reviews'];
    console.log(this.reviews.length);
  }
  linkexcute(link) {
    if (link['ref'] == 'Reviews') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.get(link['uri'], { headers: headers }).subscribe( response => this.parsereview(response));
    }
  }
  ionViewDidLoad() {  }
  decrease() {
    if (this.count > 1) {
      this.count--;
    } else {

    }
  }
  increase() {  this.count++;  }
  add() {
    this.nav.pop();
    this.prodata.setAddCart({ 'name':this.json.name,'id':this.json.id,'amount':this.count,'cost':this.json.cost,'partnerid':this.json.partnerid,'curcode':this.json.curcode });
    Toast.show("Added to Cart!", '3000', 'center').subscribe( toast => { });
  }
  link(ob) {
    if (ob.action == "GET") { return "View Partner"; }
    if (ob.action == "ADD") { return "Add To Cart (" + this.count +")"; }
    return "";
  }
}
