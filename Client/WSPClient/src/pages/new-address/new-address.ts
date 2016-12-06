import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';

@Component({
  selector: 'page-new-address',
  templateUrl: 'new-address.html'
})
export class NewAddressPage {
  addressdata: any;
  data: MyData;
  http: Http;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  nav: NavController;
  constructor(public navCtrl: NavController, mydata: MyData, h: Http) {
    this.data = mydata;
    this.nav = navCtrl;
    this.http = h;
    var userid = this.data.getObject()['id'];
    this.addressdata = {'address':'','city':'','state':'','zip':'','user':userid};
  }
  ionViewDidLoad() {  }
  process(data) {
    var res = data._body;
    var object = JSON.parse(res);
    if (object['id'] != '') {
      this.data.addAddress(object);
      this.nav.pop();
    }
  }
  create() {
    if (this.data.getType() == 0) {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.post(this.url + '/customerservice/customer/address', this.addressdata, { headers: headers }).subscribe( response => this.process(response));
    }
    if (this.data.getType() == 1) {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.post(this.url + '/partnerservice/partner/address', this.addressdata, { headers: headers }).subscribe( response => this.process(response));
    }

  }
}
