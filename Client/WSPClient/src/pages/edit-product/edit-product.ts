import { Component } from '@angular/core';
import { NavController,NavParams } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
/*
  Generated class for the EditProduct page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-product',
  templateUrl: 'edit-product.html'
})
export class EditProductPage {

  data : any;
  http: Http;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  nav: NavController;
  constructor(public navCtrl: NavController,public navParams: NavParams, h:Http) {
    this.data = navParams.get('data');
    console.log(this.data);
    this.http = h;
    this.nav = navCtrl;
  }
  update() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    var x = parseInt(this.data.invein);
    var payload = { "name":this.data.name, "description":this.data.description, "cost":this.data.cost, "curcode":this.data.curcode, "invein":x, "id":this.data.id};
    this.http.put(this.url + '/productservice/product', payload, { headers: headers }).subscribe( response => this.nav.pop());
  }

  ionViewDidLoad() {
  }

}
