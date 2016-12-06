package service.partner.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import service.AbstractRepresentation;

@XmlRootElement(name = "phone")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerPhoneRepresentation extends AbstractRepresentation {

	private String phone;
	private String type;
	private String id;
	private String user;
	
	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }
	public void setType(String type) { 	this.type = type; }
	public String getType() { return this.type; }
	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }
}
