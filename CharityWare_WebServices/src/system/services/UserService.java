package system.services;

import java.util.ArrayList;

import system.dao.managers.UserManager;
import system.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

@Path("/userService")
public class UserService {
	@GET
	@Path("/userName/{username}")
	@Produces("application/json")
	public User getUsersFromName(@PathParam("username")String username){
		System.out.println("Get Request recieved");
		UserManager userManager = new UserManager();
		ArrayList<User> holder = userManager.getUsers(username);
		System.out.println("User array list populated");
		if(holder.isEmpty())
		{
			return null;
		}
		else
		{
			return holder.get(0);
		}
	} 

	
	@GET
	@Path("/stats/ActiveUsers")
	@Produces("application/json")
	public String getSystemActiveUsers(){
		System.out.println("Get Active users Statistics");
		UserManager userManager = new UserManager();
		String stats = userManager.getSystemActiveUsers();
		System.out.println("Statistics Retrieved");
		if(stats.isEmpty())
		{
			return null;
		}
		else
		{
			return stats;
		}
	} 
	
	@GET
	@Path("/stats/UsersRegistrationActivity")
	@Produces("application/json")
	public String getUsersRegistrationActivity(){
		System.out.println("Get Users Registration Activity Statistics");
		UserManager userManager = new UserManager();
		String stats = userManager.getUsersRegistrationActivity();
		System.out.println("Statistics Retrieved");
		if(stats.isEmpty())
		{
			return null;
		}
		else
		{
			return stats;
		}
	} 
}
