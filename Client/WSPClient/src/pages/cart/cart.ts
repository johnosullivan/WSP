import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MyData } from "../../providers/my-data";
import { CheckoutPage } from '../../pages/checkout/checkout';
import { LoginPage } from '../login/login';
import { AlertController } from 'ionic-angular';

@Component({
  selector: 'page-cart',
  templateUrl: 'cart.html'
})
export class CartPage {
	prodata: MyData;
  nav: NavController;
  alertCtrl : AlertController;
  constructor(public navCtrl: NavController, pdata: MyData, alertCtrl: AlertController) {
    this.nav = navCtrl;
    this.alertCtrl = alertCtrl;
    this.prodata = pdata;
  }
  ionViewDidLoad() {  }
  checkout() {
    if (!this.prodata.getLogin()) {
        let alert = this.alertCtrl.create({
          title: 'Login Required!',
          subTitle: 'Please sign up or login to checkout',
          buttons: [
            {
              text: 'Dismiss',
              handler: data => { }
            },
            {
              text: 'Login',
              handler: data => {
                this.nav.setRoot(LoginPage);
              }
            }
          ]
        });
        alert.present();
    } else {
      this.nav.push(CheckoutPage, { item: '' })
    }
  }
  increase(data) {
    this.prodata.cartitemI(data);
  }
  decrease(data) {
    this.prodata.cartitemD(data);
  }
  delete(data) {
    this.prodata.removeCartItem(data);
  }
  total() {
    return 0 + ' USD';
  }
}
