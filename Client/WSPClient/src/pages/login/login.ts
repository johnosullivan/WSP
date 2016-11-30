import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';
import { AddressPage } from '../address/address';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {
  authType: string = "login";
  accountType: string = "customer";
  logindata: any;
  signupdata: any;
  prodata: MyData;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  http: Http;
  nav: NavController;
  constructor(public navCtrl: NavController, pdata: MyData, h: Http) {
    this.prodata = pdata;
    this.nav = navCtrl;
    this.http = h;
    this.logindata = {'username':'','password':'' };
    this.signupdata = {'username':'','password':'','repasswrd':'','email':'','accountType':'customer','first':'','middle':'','last':'','company':'','homepage':'' };
  }
  ionViewDidLoad() {  }
  logout() {
    this.prodata.setData('customer');
    this.prodata.setLogin(false);
    this.prodata.logout();
  }
  login() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/auth/login', this.logindata, { headers: headers }).subscribe( response => this.parseobject(response));
  }
  parseobject(o) {
    var json = JSON.parse(o._body);
    if (json.type == 0) {
      this.prodata.setData('customer');
      this.prodata.setLogin(true);
      this.prodata.setObject(json);
    }
    if (json.type == 1) {
      this.prodata.setData('partner');
      this.prodata.setLogin(true);
      this.prodata.setObject(json);
    }
  }
  address() {
    this.nav.push(AddressPage, { item: '' })
  }
  phone() {

  }
  signup() {
    var payload = {'first':this.signupdata.first,'middle':this.signupdata.middle,'last':this.signupdata.last,'company':this.signupdata.company,'homepage':this.signupdata.homepage,'username':this.signupdata.username,'password':this.signupdata.password,'email':this.signupdata.email};
    var typeint = this.signupdata.accountType;
    if (typeint == 'customer') { payload['type'] = 0; }
    if (typeint == 'partner') { payload['type'] = 1; }
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/auth/registration', payload, { headers: headers }).subscribe( response => this.parseobject(response));
  }
}
