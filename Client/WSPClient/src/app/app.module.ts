import { NgModule } from '@angular/core';
import { IonicApp, IonicModule } from 'ionic-angular';
import { MyApp } from './app.component';
import { Search } from '../pages/search/search';
import { Product } from '../pages/product/product';
import { LoginPage } from '../pages/login/login';
import { MyData } from "../providers/my-data";
import { CartPage } from '../pages/cart/cart';
import { CheckoutPage } from '../pages/checkout/checkout';
import { AddressPage } from '../pages/address/address';
import { PhonePage } from '../pages/phone/phone';
import { NewAddressPage } from '../pages/new-address/new-address';
import { MyOrdersPage } from '../pages/my-orders/my-orders';
import { EditAddressPage } from '../pages/editaddress/editaddress';

@NgModule({
  declarations: [
    MyApp,
    Search,
    Product,
    LoginPage,
    CartPage,
    CheckoutPage,
    AddressPage,
    PhonePage,
    NewAddressPage,
    MyOrdersPage,
    EditAddressPage
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    Search,
    Product,
    LoginPage,
    CartPage,
    CheckoutPage,
    AddressPage,
    PhonePage,
    NewAddressPage,
    MyOrdersPage,
    EditAddressPage
  ],
  providers: [
  MyData
  ]
})
export class AppModule {}
