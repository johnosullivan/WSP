import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { Http , Headers } from '@angular/http';

/*
  Generated class for the AccountDetails page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-account-details',
  templateUrl: 'account-details.html'
})
export class AccountDetailsPage {

  prodata: MyData;
  data : any;
  type : any;
  http: Http;
  nav : NavController;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, p:MyData, h:Http) {
    this.prodata = p;
    this.nav = navCtrl;
    this.data = this.prodata.getObject();
    this.type = this.data['type'];
    this.http = h;
    console.log(this.data);
  }

  update(d) {
    var json = JSON.parse(d._body);
    this.prodata.update(json);
    this.nav.pop();
  }
  save() {

    if (this.type == 0) {
      var payload = { "id": this.data['id'], "lastName":this.data['last'],"middleName":this.data['middle'],"firstName":this.data['first'],"email":this.data['email'], "propic":"" };
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.put(this.url + '/customerservice/customer', payload, { headers: headers }).subscribe( response => this.update(response));

    }
    if (this.type == 1) {
      var payload = { "id": this.data['id'], "lastName":this.data['last'],"middleName":this.data['middle'],"firstName":this.data['first'],"email":this.data['email'], "propic":"" };
      var headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      this.http.put(this.url + '/parterservice/partner', payload, { headers: headers }).subscribe( response => this.update(response));
    }

  }


  ionViewDidLoad() {

  }

}
