import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";

/*
  Generated class for the NewProduct page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-product',
  templateUrl: 'new-product.html'
})
export class NewProductPage {

  data: any;
  http: Http;
  nav: NavController;
  prodata: MyData;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController,h: Http, d:MyData) {
    this.http = h;
    this.nav = navCtrl;
    this.prodata = d;
    this.data = { "name":"iPhone 20", "description":"This is a cool iphone", "cost":750, "curcode":"US", "invein":10, "partnerid":this.prodata.getObject()['id'] };
  }

  ionViewDidLoad() {

  }

  create() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/productservice/product', this.data, { headers: headers }).subscribe( response => this.nav.pop());
  }

}
