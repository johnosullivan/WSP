import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewAddressPage } from '../new-address/new-address';
import { MyData } from "../../providers/my-data";

@Component({
  selector: 'page-address',
  templateUrl: 'address.html'
})
export class AddressPage {
  nav : NavController;
  data : MyData;
  constructor(public navCtrl: NavController, data : MyData) {
    this.nav = navCtrl;
    this.data = data;
  }
  ionViewDidLoad() {  }
  create() { this.nav.push(NewAddressPage, { item: '' }) }
}
