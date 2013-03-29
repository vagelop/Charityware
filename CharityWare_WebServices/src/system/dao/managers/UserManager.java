package system.dao.managers;

import shared.dao.util.ConnectionManager;
import system.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserManager {

	//private String DBConfname;
	private ConnectionManager conn;

	public UserManager(){
		conn = new ConnectionManager("");
	}

	public  ArrayList<User> getUsers(String name){
		ArrayList<User> user = (ArrayList<User>)conn.getTable("User where userName = '"+ name+"'");
		return user;
	}


	public User getUser(Integer id){
		User user = (User)conn.get(User.class,id);
		return user;
	}

	public String getSystemActiveUsers(){
		//String result = "";
		ArrayList<User> activeUser = (ArrayList<User>)conn.getTable("User where isActive=1");
		ArrayList<User> inactiveUser = (ArrayList<User>)conn.getTable("User where isActive=0");

		StringBuilder finalresult = new StringBuilder();

		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],", "Active Accounts", activeUser.size()));
		finalresult.append(String.format("[\"%s\",%d]", "Inactive Accounts", inactiveUser.size()));
		finalresult.append(']');

		return finalresult.toString();
	}

	public String getUsersRegistrationActivity(){
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
