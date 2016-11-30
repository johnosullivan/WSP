import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';
import { Search } from '../pages/search/search';
import { LoginPage } from '../pages/login/login';
import { MyData } from "../providers/my-data";
import { CartPage } from '../pages/cart/cart';
import { MyOrdersPage } from '../pages/my-orders/my-orders';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  rootPage: any = LoginPage;
  pages: Array<{title: string, component: any, icon: string}>;
  pcustomer: Array<{title: string, component: any, icon: string}>;
  ppartner: Array<{title: string, component: any, icon: string}>;
  prodata: MyData;
  constructor(public platform: Platform, pdata: MyData) {
    this.initializeApp();
    this.prodata = pdata;
    this.pages = [
      { title: 'Search', component: Search, icon:'search' },
      { title: 'Cart', component: CartPage, icon:'cart' },
      { title: 'Login / Signup', component: LoginPage, icon:'log-in' }
    ];
    this.pcustomer = [
      { title: 'Search', component: Search, icon:'search' },
      { title: 'Cart', component: CartPage, icon:'cart' },
      { title: 'My Orders', component: MyOrdersPage, icon:'list-box' },
      { title: 'My Account', component: LoginPage, icon:'person' },
    ];
    this.ppartner = [
      { title: 'Search', component: Search, icon:'search' },
      { title: 'Orders', component: LoginPage, icon:'list-box' },
      { title: 'My Products', component: LoginPage, icon:'options' },
      { title: 'My Account', component: LoginPage, icon:'person' }
    ];
  }
  getPages() {
    var str: string = this.prodata.getData();
    if (str == 'customer') { return this.pcustomer; }
    if (str == 'partner') { return this.ppartner; }
    return [];
  }
  initializeApp() {
    this.platform.ready().then(() => {
      StatusBar.styleDefault();
      Splashscreen.hide();
    });
  }
  openPage(page) {
    this.nav.setRoot(page.component);
  }
}
