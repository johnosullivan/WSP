import { Component } from '@angular/core';
import { NavController,NavParams } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";
/*
  Generated class for the ReviewOrder page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-review-order',
  templateUrl: 'review-order.html'
})
export class ReviewOrderPage {

  product : any;
  rate: any;
  review : any;
  prodata : any;
  data : any;
  http: Http;
  products : any;
  nav : NavController;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController,navParams: NavParams, h:Http, p:MyData) {
      this.data = navParams.get('order');
      console.log(this.data);
      this.nav = navCtrl;
      this.product = '';
      this.review = '';
      this.rate = '';
      this.http = h;
      this.prodata = p;
      this.products = this.data['items'];
      console.log(this.products);
  }

  create() {
    var name = this.prodata.getObject()['first'] + " " + this.prodata.getObject()['middle'] + " " + this.prodata.getObject()['last'];
    var payload = { "order":this.data['id'], "review":this.review, "stars":parseInt(this.rate), "product":this.product, "name": name };
    console.log(payload);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/reviewservice/review', payload, { headers: headers }).subscribe( response => this.nav.pop());
  }

  ionViewDidLoad() {
  }

}
