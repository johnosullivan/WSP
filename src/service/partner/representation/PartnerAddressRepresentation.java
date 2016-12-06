package service.partner.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import service.AbstractRepresentation;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerAddressRepresentation extends AbstractRepresentation {

	private String address;
	private String city;
	private String state;
	private String id;
	private String user;
	private int zip;
	// Get and Set Methods
	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }
	public void setUser(String user) { 	this.user = user; }
	public String getUser() { return this.user; }
	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }
	public void setCity(String city) { this.city = city; }
	public String getCity() { return this.city; }
	public void setState(String state) { this.state = state; }
	public String getState() { return this.state; }
	public void setZip(int zip) { this.zip = zip; }
	public int getZip() { return this.zip; }
	
}

