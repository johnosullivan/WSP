import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewPhonePage } from '../new-phone/new-phone';
import { MyData } from "../../providers/my-data";

@Component({
  selector: 'page-phone',
  templateUrl: 'phone.html'
})
export class PhonePage {
  nav : NavController;
  data : MyData;
  constructor(public navCtrl: NavController, d: MyData) {
    this.nav = navCtrl;
    this.data = d;
    console.log(this.data.getPhones());
  }
  ionViewDidLoad() {  }
  create() {
    this.nav.push(NewPhonePage);
  }
}
