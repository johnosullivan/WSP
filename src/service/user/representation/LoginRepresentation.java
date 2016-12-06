package service.user.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "login")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class LoginRepresentation {

	private String username;
	private String password;
	
	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }
	
	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }
}
