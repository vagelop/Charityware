package charity.services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import charity.dao.managers.UserTypeManager;
import charity.model.UserType;

@Path("/userTypeService")
public class UserTypeService {
	
	@GET
    @Path("/charityConfig/{DBConfigPath}/userTypes/")
    @Produces("application/json")
    public List<UserType> getUserTypes(@PathParam("DBConfigPath")String DBConfigPath){
    	UserTypeManager userTypeManager = new UserTypeManager(DBConfigPath);
    	return new ArrayList<UserType>(userTypeManager.getUserTypes());
	  }
	
    @POST
  	@Path("/charityConfig/{DBConfigPath}/{userType}")
      public void postUserType(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("userType") String userType){
      	//UserType uType = new UserType();
    	UserTypeManager utManager = new UserTypeManager(DBConfigPath);
    	utManager.addUserType(userType);
      }

}
