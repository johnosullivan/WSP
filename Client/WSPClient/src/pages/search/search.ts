import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http , Headers } from '@angular/http';
import { Product } from '../product/product';
import { MyData } from "../../providers/my-data";

@Component({
  selector: 'page-search',
  templateUrl: 'search.html'
})
export class Search {
	searchQuery: string = '';
  items: any;
  result: any;
  http:Http;
  prodata: MyData;
	searchresults : any;
	nav: any;
  url: string = 'https://fall2016wsp.herokuapp.com/api';
  //url: string = 'http://localhost:8080/WSP/api';
  constructor(public navCtrl: NavController, http:Http, pdata: MyData) {
  	this.http = http;
  	this.nav = navCtrl;
  	this.result = {'searchterm':'','results':''};
    this.prodata = pdata;
    this.searchresults = [];
  }
  ionViewDidLoad() {  }
  getName(data) { return data.getAttribute('name'); }
  linkexcute(ob) {
    if (ob.action == "GET") {
			var headers = new Headers();
			headers.append('Accept', 'application/json');
			this.http.get(ob.url, { headers: headers }).subscribe(
      		response => this.nav.push(Product, { item: response })
			);
		}
  }
  link(ob) {
    if (ob.action == "GET") { return "View"; }
    return "";
  }
  parse(data) {
   	var res = data._body;
   	var datas = JSON.parse(res);
    this.searchresults = datas.results;
  }
  search() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    this.http.post(this.url + '/productservice/search', {searchterm:this.searchQuery}, { headers: headers }).subscribe(
      response => this.parse(response)
    );
    this.searchQuery = '';
  }
}
