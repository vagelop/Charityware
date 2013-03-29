package charity.dao.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.dao.util.ConnectionManager;
import charity.model.UserType;

public class UserTypeManager {
	
	private ConnectionManager conn;
	public UserTypeManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	
	public UserType getUserType(Integer userTypeId){
		UserType userType = (UserType)conn.get(UserType.class,userTypeId);
		return userType;
	}
	
	public List<UserType> getUserTypes(){
		List<UserType> results = new  ArrayList<UserType>();
		List<UserType> userTypes = (List<UserType>)conn.getTable("UserType where isActive = 1");
		Iterator<UserType> userTypes_iter = userTypes.iterator();
		while(userTypes_iter.hasNext()){
			UserType userType_temp = userTypes_iter.next();
			if(userType_temp.getIsActive()){
				results.add(userType_temp);
			}
		}
		return results;
	}


	public Integer addUserType (String userType) {
		UserType utype = new UserType();
		utype.setUserType(userType);
		utype.setDescription(userType);
		utype.setIsActive(true);
		return (Integer) conn.transaction("save",utype);	
	}
}
