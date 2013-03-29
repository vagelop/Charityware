package charity.dao.managers;

import charity.model.Form;
import charity.model.FormPermissions;
import charity.model.User;
import charity.model.UserType;
import shared.dao.util.ConnectionManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class UserManager {

	private String DBConfname;
	private ConnectionManager conn;

	public UserManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
		this.DBConfname=DBConfname;
	}

	public List<User> retrieve(){		
		List<User> users = (List<User>) conn.getTable("User");
		return users;
	}

	public  List<User> getUsers(String name){
		List<User> user = (List<User>)conn.getTable("User where userName = '"+ name +"'");
		return user;
	}


	public Integer addUserSample (String name,String pass) {
		User user = new User (name,pass);
		return (Integer) conn.transaction("save",user);

	}

	public Integer addUser (String name,String pass,String email, int userTypeId ) {
		UserTypeManager ut =new UserTypeManager(this.DBConfname);
		UserType usertype = (UserType)ut.getUserType(new Integer (userTypeId));
		User user = new User(name,pass);
		user.setUserEmail(email);
		user.setUserType(usertype);
		user.setIsActive(true);
		return (Integer) conn.transaction("save",user);	
	}

	public User getUser(Integer id){
		User user = (User)conn.get(User.class,id);
		return user;
	}


	/*public static ArrayList<User> getUsers(String name){
		ArrayList<User> user = (ArrayList<User>)ConnectionManager.getTable("User where userName = '" + name+"'");
		return user;
	}*/

	public  void updateUserPassword (Integer userId,String userPassword ) {
		User user = (User) conn.get(User.class, userId);
		user.setUserPassword(userPassword);
		conn.transaction("update",user);
	}

	public Map<Integer,List<String>> getForms(){
		Map<Integer,List<String>> results = new TreeMap<Integer,List<String>>();
		List<User> users = (List<User>) conn.getTable("User where isActive = 1");

		ArrayList<FormPermissions> formpermissions = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");

		Iterator<User> iter = users.iterator();			
		while(iter.hasNext()){
			ArrayList<String> userdata = new ArrayList<String>();
			User user_cur = iter.next();
			Iterator<FormPermissions> formperm_iter = formpermissions.iterator();
			String value = null;
			while(formperm_iter.hasNext()){
				FormPermissions formperm_cur = formperm_iter.next();

				if(value==null){
					value = formperm_cur.getPk().getForm().getFormName();
				}else{
					value = value+","+formperm_cur.getPk().getForm().getFormName();
				}
			}
			userdata.add(user_cur.getUserName());
			userdata.add(user_cur.getUserType().getUserType());
			userdata.add(user_cur.getUserEmail());
			userdata.add(value);			
			results.put(user_cur.getUser_id(),userdata);
		}
		return results;
	}

	public List<Form> getFormEntities(String username){

		ArrayList<User> users = (ArrayList<User>)conn.getTable("User where userName = '" + username+"'");
		User user = users.get(0);
		List<Form> results = new ArrayList<Form>();
		ArrayList<FormPermissions> formPermissionsList = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");
		Iterator<FormPermissions> formPermissions_iter = formPermissionsList.iterator();
		while(formPermissions_iter.hasNext()){
			FormPermissions formPermissions = formPermissions_iter.next();

			if(user.getUserType().getUserTypeId().equals(formPermissions.getPk().getUser_type().getUserTypeId())){

				results.add(formPermissions.getPk().getForm());
			}

		}
		return results;
	}

	public void deactivateUserAccount(Integer userId){
		User user = (User) conn.get(User.class, userId);
		user.setIsActive(false);
		user = (User) conn.merge(user);
		conn.transaction("update",user);
	}

	public String getCharityActiveUsers(){
		ArrayList<User> activeUser = (ArrayList<User>)conn.getTable("User where isActive=1");
		ArrayList<User> inactiveUser = (ArrayList<User>)conn.getTable("User where isActive=0");

		StringBuilder finalresult = new StringBuilder();

		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],", "Active Accounts", activeUser.size()));
		finalresult.append(String.format("[\"%s\",%d]", "Inactive Accounts", inactiveUser.size()));
		finalresult.append(']');

		return finalresult.toString();
	}

	public String getCharityUsersRegistrationActivity(){
		ArrayList<User> Users = (ArrayList<User>)conn.getTable("User");
		StringBuilder finalresult = new StringBuilder();
		Integer justCreated = 0, oneDay = 0, oneWeek = 0, oneMonth = 0, others = 0;

		for (User u : Users) {

			long lDuration = (System.currentTimeMillis() - u.getDateCreated().getTime()) / 100000;
			if (lDuration > 36 && lDuration <= 864) {
				oneDay++;
			} else if (lDuration <= 36) {
				justCreated++;
			} else if (lDuration > 864 && lDuration <= 6048) {
				oneWeek++;
			} else if (lDuration > 6048 && lDuration <= 25920) {
				oneMonth++;
			} else {
				others++;
			}

		}

		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],","Added an Hour ago", justCreated));
		finalresult.append(String.format("[\"%s\",%d],","Added one Day ago", oneDay));
		finalresult.append(String.format("[\"%s\",%d],","Added one Week ago", oneWeek));
		finalresult.append(String.format("[\"%s\",%d],","Added one Month ago", oneMonth));
		finalresult.append(String.format("[\"%s\",%d]", "Added Before", others));
		finalresult.append(']');

		return finalresult.toString();
	}
}
