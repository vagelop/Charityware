package charity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import charity.dao.managers.UserManager;
import charity.model.Form;
import charity.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;



@Path("/userService")
public class UserService {
	@GET
	@Path("/charityConfig/{DBConfigPath}/userName/{username}")
	@Produces("application/json")
	public User getUsersFromName(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("username")String username){
		System.out.println("Get Request recieved");
		UserManager userManager = new UserManager(DBConfigPath);

		List<User> holder = userManager.getUsers(username);
		System.out.println("User array list populated");

		return holder.get(0);    
	}


	@GET
	@Path("/charityConfig/{DBConfigPath}/users/")
	@Produces("application/json")
	public List<User> listUsersJSON(@PathParam("DBConfigPath")String DBConfigPath){
		UserManager userManager = new UserManager(DBConfigPath);
		return new ArrayList<User>(userManager.retrieve());
	}

	@GET
	@Path("/charityConfig/{DBConfigPath}/user/{userid}")
	@Produces("application/json")
	public User getUserJSON(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("userid")Integer userid){
		UserManager userManager = new UserManager(DBConfigPath);
		return userManager.getUser(userid);       
	}

	@POST
	@Path("/charityConfig/{DBConfigPath}/user/{name}/pass/{pass}")
	public void addUser(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("name") String name,@PathParam("pass") String pass){
		UserManager userManager = new UserManager(DBConfigPath);
		userManager.addUserSample(name, pass);
	}

	@POST
	@Path("/charityConfig/{DBConfigPath}/{name}/{pass}/{email}/{userType}")
	public void postUser(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("name") String name,@PathParam("pass") String pass,
			@PathParam("email") String email,@PathParam("userType") int userType){
		UserManager userManager = new UserManager(DBConfigPath);
		userManager.addUser(name, pass, email, userType);
	}


	@GET
	@Path("/charityConfig/{DBConfigPath}/users/forms/")
	@Produces("application/json")
	public GenericEntity<Map<Integer,List<String>>> getForms(@PathParam("DBConfigPath")String DBConfigPath){
		UserManager userManager = new UserManager(DBConfigPath);
		Map<Integer,List<String>> map = (Map<Integer,List<String>>) userManager.getForms();
		GenericEntity<Map<Integer, List<String>>> entity = new GenericEntity<Map<Integer, List<String>>>(map){};
		return entity;
	}

	@GET
	@Path("/charityConfig/{DBConfigPath}/users/formEntities/{username}")
	@Produces("application/json")
	public GenericEntity<List<Form>> getFormEntities(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("username")String username){
		UserManager userManager = new UserManager(DBConfigPath);
		List<Form> list = userManager.getFormEntities(username);
		GenericEntity<List<Form>> entity = new GenericEntity<List<Form>>(list){};
		//Map<FormKey,List<FormFields>> map = userManager.getFormEntities2(username);
		//GenericEntity<Map<FormKey,List<FormFields>>> entity = new GenericEntity<Map<FormKey,List<FormFields>>>(map){};
		return entity;
	}

	@POST
	@Path("/deactivateUserAccount/{DBConfigPath}/{userId}")
	@Produces("application/json")
	public Boolean postDeactivateUserAccount(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("userId") String userId){		 
		try{ 
			//isActive = 0
			System.out.println("Get User ID"); 
			int userID = Integer.parseInt(userId);
			UserManager userManager = new UserManager(DBConfigPath);
			userManager.deactivateUserAccount(userID);
			System.out.println("User Deactivated"); 
			return true;
		}catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

	}

	@GET
	@Path("/stats/ActiveUsers/{DBConfigPath}")
	@Produces("application/json")
	public String getSystemActiveUsers(@PathParam("DBConfigPath")String DBConfigPath){
		System.out.println("Get Active users Statistics");
		UserManager userManager = new UserManager(DBConfigPath);
		String stats = userManager.getCharityActiveUsers();
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
	@Path("/stats/CharityUsersRegistrationActivity/{DBConfigPath}")
	@Produces("application/json")
	public String getUsersRegistrationActivity(@PathParam("DBConfigPath")String DBConfigPath){
		System.out.println("Get Users Registration Activity Statistics");
		UserManager userManager = new UserManager(DBConfigPath);
		String stats = userManager.getCharityUsersRegistrationActivity();
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
