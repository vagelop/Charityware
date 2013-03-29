package shared.dao.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resources")
public class Resources {
	private  String mySQLRootUser;
	private  String mySQLRootPassword;
	private  String mySQLDriver;
	private  String mySQLConUrl;
	
	@XmlElement
	public String getMySQLRootUser() {
		return mySQLRootUser;
	}
	public void setMySQLRootUser(String mySQLRootUser) {
		this.mySQLRootUser = mySQLRootUser;
	}
	@XmlElement
	public String getMySQLRootPassword() {
		return mySQLRootPassword;
	}
	public void setMySQLRootPassword(String mySQLRootPassword) {
		this.mySQLRootPassword = mySQLRootPassword;
	}
	@XmlElement
	public String getMySQLDriver() {
		return mySQLDriver;
	}
	public void setMySQLDriver(String mySQLDriver) {
		this.mySQLDriver = mySQLDriver;
	}
	@XmlElement
	public String getMySQLConUrl() {
		return mySQLConUrl;
	}
	public void setMySQLConUrl(String mySQLConUrl) {
		this.mySQLConUrl = mySQLConUrl;
	}	
}
