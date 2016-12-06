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
import { ViewAddressPage } from '../pages/view-address/view-address';
import { ProcessOrderPage } from '../pages/process-order/process-order';
import { MyProductsPage } from '../pages/my-products/my-products';
import { OrdersPage } from '../pages/orders/orders';
import { NewProductPage } from '../pages/new-product/new-product';
import { AccountDetailsPage } from '../pages/account-details/account-details';
import { NewPhonePage } from '../pages/new-phone/new-phone';
import { ViewOrderPage } from '../pages/view-order/view-order';
import { ReviewOrderPage } from '../pages/review-order/review-order';
import { EditProductPage } from '../pages/edit-product/edit-product';

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
    EditAddressPage,
    ViewAddressPage,
    ProcessOrderPage,
    MyProductsPage,
    OrdersPage,
    NewProductPage,
    AccountDetailsPage,
    NewPhonePage,
    ViewOrderPage,
    ReviewOrderPage,
    EditProductPage
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
    EditAddressPage,
    ViewAddressPage,
    ProcessOrderPage,
    MyProductsPage,
    OrdersPage,
    NewProductPage,
    AccountDetailsPage,
    NewPhonePage,
    ViewOrderPage,
    ReviewOrderPage,
    EditProductPage
  ],
  providers: [
  MyData
  ]
})
export class AppModule {}
