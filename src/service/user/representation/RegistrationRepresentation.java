package service.user.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "registration")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RegistrationRepresentation {

	private String first;
	private String middle;
	private String last;
	private String email;
	private String username;
	private String password;
	private String company;
	private String homepage;
	private int type;

	public void setFirst(String first) { this.first = first; }
	public String getFirst() { return this.first; }
	
	public void setMiddle(String middle) { this.middle = middle; }
	public String getMiddle() { return this.middle; }
	
	public void setLast(String last) { this.last = last; }
	public String getLast() { return this.last; }
	
	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }
	
	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }
	
	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }
	
	public void setCompany(String company) { this.company = company; }
	public String getCompany() { return this.company; }
	
	public void setHomepage(String homepage) { this.homepage = homepage; }
	public String getHomepage() { return this.homepage; }
	
	public void setType(int type) { this.type = type; }
	public int getType() { return this.type; }
}
