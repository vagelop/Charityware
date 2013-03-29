package env.Entities;

import java.sql.Timestamp;


public class UserType {

	private Integer userTypeId;
	private String userType;
	private String description;
	private Boolean isActive;
//	private User user;
	
/*	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

	
}
