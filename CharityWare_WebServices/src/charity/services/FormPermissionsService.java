package charity.services;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import charity.dao.managers.FormPermissionsManager;

@Path("/formPermissionsService")
public class FormPermissionsService {
	
	@GET
	@Path("/{DBConfigPath}/formPermissions/{userTypeId}")
	@Produces("application/json")
	public List<Integer> getformPermissons(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("userTypeId")Integer userTypeId){
		FormPermissionsManager formPermissionsManager = new FormPermissionsManager(DBConfigPath);
		List<Integer> formIds = (List<Integer>) formPermissionsManager.getFormPermissions(userTypeId);
		return formIds;
		
	}
	
		
	 @POST
	 @Path("/formPermissions/addPermissions/{DBConfigPath}/{userTypeId}/{FormId}")
	 @Produces("application/json")
	 public Boolean postFormPermissions(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("userTypeId")Integer userTypeId,@PathParam("FormId")Integer FormId){		 
		 Boolean status = false;
		 try
		 {
			FormPermissionsManager formPermissionsManager = new FormPermissionsManager(DBConfigPath);
			status = (Boolean) formPermissionsManager.postFormPermissions(userTypeId, FormId);
			return status;
		 }catch (Exception ex)
		 {
			 ex.printStackTrace();
			 
			return status;
		 } 	
	 }
	

}
