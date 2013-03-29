package system.model;

import java.sql.Date; 
import java.util.Calendar;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;

import shared.model.util.PasswordEncryption;

@Entity
@Audited
@XmlRootElement(name = "user")
public class User {

	private Integer user_id;
	private String userName;
	private UserType userType;
	private String userPassword;
	private String salt;
	private String userEmail;
	private Date dateCreated;
	private Boolean isActive;
	

	public User(){}
	public User(String name, String pass) {
		this.userName=name;
		this.isActive=true;
		this.salt= PasswordEncryption.createSalt();
		this.userPassword=PasswordEncryption.encryptPassword(pass, salt);
		this.dateCreated = new Date(Calendar.DATE);
		this.userType = new UserType();
		this.userType.setUserType("Charity_Administrator");
		this.userType.setUserTypeId(1);
		this.userType.setIsActive(true);		
	}
	
	
	
	@XmlElement
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@XmlElement
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@XmlElement(name = "userType")
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	@XmlElement
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@XmlElement
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@XmlElement
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}

