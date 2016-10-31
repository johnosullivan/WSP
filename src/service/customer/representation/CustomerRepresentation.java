package service.customer.representation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRepresentation {
	
	private String id;
	private String lastName;
	private String middleName;
	private String email;
	private String firstName;

	private String propic;
	public String getPropic() {
		return propic;
	}

	public void setPropic(String propic) {
		this.propic = propic;
	}
	
	private ArrayList<CustomerAddressRepresentation> addresses;
	private ArrayList<CustomerPhoneRepresentation> phones;
	
	public CustomerRepresentation() {}

	public ArrayList<CustomerPhoneRepresentation> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<CustomerPhoneRepresentation> phones) {
		this.phones = phones;
	}
	
	public ArrayList<CustomerAddressRepresentation> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<CustomerAddressRepresentation> addresses) {
		this.addresses = addresses;
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

