import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewProductPage } from '../new-product/new-product';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";
import { EditProductPage } from '../edit-product/edit-product';

@Component({
  selector: 'page-my-products',
  templateUrl: 'my-products.html'
})
export class MyProductsPage {

  nav : NavController;
  http : Http;
  data: any;
  prodata: any;
  userid : any;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, h:Http, p: MyData) {
    this.nav = navCtrl;
    this.http = h;
    this.prodata = p;
    this.data = [];
    this.userid = this.prodata.getObject()['id'];
  }
  parse(d) {
    var object = JSON.parse(d._body);
    this.data = object['products'].reverse();
    console.log(this.data);
  }
  ionViewWillEnter() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.get(this.url + '/productservice/products/partner/' + this.userid, { headers: headers }).subscribe( response => this.parse(response));

  }
  edit(product) {
    this.nav.push(EditProductPage,{data:product});
  }
  create() {
    this.nav.push(NewProductPage,{item:''})
  }

}
