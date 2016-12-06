import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewAddressPage } from '../new-address/new-address';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';
import { ViewAddressPage } from '../view-address/view-address';
@Component({
  selector: 'page-address',
  templateUrl: 'address.html'
})
export class AddressPage {
  nav : NavController;
  data : MyData;
  http: Http;
  constructor(public navCtrl: NavController, data : MyData, h: Http) {
    this.nav = navCtrl;
    this.data = data;
    this.http = h;
  }
  ionViewDidLoad() {  }
  linkexcute(link) {
    console.log(link);
    if (link['method'] == 'GET') {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.get(link.uri, { headers: headers }).subscribe( response => this.nav.push(ViewAddressPage,{data:response}));
    }
    if (link['method'] == 'DELETE') {

    }
  }
  create() { this.nav.push(NewAddressPage, { item: '' }) }
}
