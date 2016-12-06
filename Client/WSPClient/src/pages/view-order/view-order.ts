import { Component } from '@angular/core';
import { NavController,NavParams } from 'ionic-angular';

/*
  Generated class for the ViewOrder page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-view-order',
  templateUrl: 'view-order.html'
})
export class ViewOrderPage {

  data : any;
  constructor(public navCtrl: NavController,navParams: NavParams) {
    this.data = navParams.get('order');
  }

  ionViewDidLoad() {
  }

}
