package com.ws.project.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation {

	public ProductRepresentation() {}
	
	private String name;
	private String description;
	private int cost;
	private int invein;
	private String curcode;
	private String partner;
	private String id;
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setInvein(int invein) {
		this.invein = invein;
	}
	
	public int getInvein() {
		return invein;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}
	
	public String getCurcode() {
		return curcode;
	}
	
	public void setPartnerid(String partnerid) {
		this.partner = partnerid;
	}
	
	public String getPartnerid() {
		return partner;
	}
	
}
