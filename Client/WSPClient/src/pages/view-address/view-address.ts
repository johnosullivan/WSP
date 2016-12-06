import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { MyData } from "../../providers/my-data";

/*
  Generated class for the ViewAddress page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-view-address',
  templateUrl: 'view-address.html'
})
export class ViewAddressPage {

  data:any;
  http:any;
  prodata: MyData;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController,public navParams: NavParams, h:Http,p:MyData) {
    this.data = navParams.get('data');
    this.prodata = p;
    var str = this.data._body;
    this.http = h;
    this.data = JSON.parse(str);

  }
  update() {

  }
  linkexcute(link) {
    console.log(link);
    if (link['method'] == "PUT") {

    }
    if (link['method'] == "DELETE") {

    }
  }

  ionViewDidLoad() {

  }

}
