package com.ws.project.product.service.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "search")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class SearchRepresentation {

	private String searchterm;
	private ArrayList<ProductRepresentation> results;
	
	public String getSearch() {
		return this.searchterm;
	}
	
	public void setSearch(String searchterm) {
		this.searchterm = searchterm;
	}
	
	public ArrayList<ProductRepresentation> getResults() {
		return this.results;
	}
	
	public void setResults(ArrayList<ProductRepresentation> results) {
		this.results = results;
	}
	
}
