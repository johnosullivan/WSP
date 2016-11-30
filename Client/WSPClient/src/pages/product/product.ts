import { Component } from '@angular/core';
import { MyData } from "../../providers/my-data";
import { NavController, NavParams } from 'ionic-angular';
import { Toast } from 'ionic-native';

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
  constructor(public navCtrl: NavController, public navParams: NavParams, pdata: MyData) {
    this.selectedItem = navParams.get('item');
    this.title = '';
    this.nav = navCtrl;
    this.prodata = pdata;
    console.log(this.selectedItem._body);
    this.json = JSON.parse(this.selectedItem._body);
    console.log(this.json);
    this.title = this.json.name;
    this.count = 1;
  }
  ionViewDidLoad() {  }
  decrease() {  this.count = this.count - 1;  }
  increase() {  this.count = this.count + 1;  }
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
