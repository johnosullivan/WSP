package service.partner.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name = "partner")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerRepresentation {
	
	private String id;
	private String lastName;
	private String middleName;
	private String email;
	private String firstName;
	private String company;
	private String homepage;
	
	private ArrayList<PartnerAddressRepresentation> addresses;
	private ArrayList<PartnerPhoneRepresentation> phones;
	

	public ArrayList<PartnerPhoneRepresentation> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<PartnerPhoneRepresentation> phones) {
		this.phones = phones;
	}
	
	public ArrayList<PartnerAddressRepresentation> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<PartnerAddressRepresentation> addresses) {
		this.addresses = addresses;
	}
	
	
	public PartnerRepresentation() {}
	
	public String getHomepage() {
		return homepage;
	}
	
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

