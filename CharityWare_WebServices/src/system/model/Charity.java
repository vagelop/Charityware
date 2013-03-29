package system.model;


import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import system.model.User;

@Entity
@Audited
@XmlRootElement(name = "charity")
public class Charity {
	
	private Integer charity_id;
	private String charity_name;
	private String charity_description;
	private String address_line1;
	private String address_line2;
	private String location;
	private String postcode;
	private String email;
	private String phone;
	private User user;
	private String registration_no;
	private String account_no;
	private String connection_string;
	private Boolean isVerified;	
	private Boolean isActive;
	private Date dateCreated;
	
	public Charity(){}
	
	public Charity(String Charity_Name, String Charity_Description,String Address_Line1,String Address_Line2,String Location,String Postcode,String Email,String Phone,String Registration_No){		
		this.charity_name = Charity_Name;
		this.charity_description = Charity_Description;
		this.address_line1 = Address_Line1;
		this.address_line2 = Address_Line2;
		this.location = Location;
		this.postcode = Postcode;
		this.email = Email;
		this.phone = Phone;
		this.registration_no = Registration_No;
		this.dateCreated = new Date(Calendar.DATE);
	}
	
	@XmlElement
	public Integer getCharity_id() {
		return charity_id;
	}
	public void setCharity_id(Integer charity_id) {
		this.charity_id = charity_id;
	}
	
	@XmlElement
	public String getCharity_name() {
		return charity_name;
	}
	public void setCharity_name(String charity_name) {
		this.charity_name = charity_name;
	}		
	@XmlElement
	public String getCharity_description() {
		return charity_description;
	}
	public void setCharity_description(String charity_description) {
		this.charity_description = charity_description;
	}
	@XmlElement
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	
	@XmlElement
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	
	@XmlElement
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlElement
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@XmlElement(name = "user")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
	@XmlElement
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	
	@XmlElement
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	@XmlElement
	public String getConnection_string() {
		return connection_string;
	}
	public void setConnection_string(String connection_string) {
		this.connection_string = connection_string;
	}
	
	@XmlElement
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@XmlElement
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}

