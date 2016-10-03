package com.ws.project;

import java.util.ArrayList;

//This is the report object that is built for the report of the a product.
public class Report {
	//Stores the summaries of report
	private ArrayList<String> data;
	//Default constructor for report.
	public Report() {
		data = new ArrayList<String>();
	}
	//Add Summary from each report in database
	public void addReport(String str) {
		data.add(str);
	}
	// Get the summary from the database
	public ArrayList<String> getSummary() {
		return this.data;
	}
	
}
