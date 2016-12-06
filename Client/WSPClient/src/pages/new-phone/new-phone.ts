import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';
/*
  Generated class for the NewPhone page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-phone',
  templateUrl: 'new-phone.html'
})
export class NewPhonePage {
  data : any;
  prodata: MyData;
  http: Http;
  nav: NavController;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController,mydata: MyData, h: Http) {
    this.data = { "phone":"","type":"" };
    this.prodata = mydata;
    this.http = h;
    this.nav = navCtrl;
  }

  ionViewDidLoad() {
  }
  process(data) {
    var res = data._body;
    var object = JSON.parse(res);
    if (object['id'] != '') {
      this.nav.pop();
      this.prodata.addPhones(object);
    }
  }
  create() {
    var userid = this.prodata.getObject()['id'];

    if (this.prodata.getType() == 0) {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var payload = { "type":this.data['type'],"phone":this.data['phone'],"user":userid };
      this.http.post(this.url + '/customerservice/customer/phone', payload, { headers: headers }).subscribe( response => this.process(response));
    }
    if (this.prodata.getType() == 1) {
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      var payload = { "type":this.data['type'],"phone":this.data['phone'],"user":userid };
      this.http.post(this.url + '/partnerservice/partner/phone', payload, { headers: headers }).subscribe( response => this.process(response));
    }
  }

}
