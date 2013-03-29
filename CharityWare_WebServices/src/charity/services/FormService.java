package charity.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import charity.dao.managers.FormManager;
import charity.model.Form;

@Path("/formService")
public class FormService {
	
	@GET
    @Path("/forms/{DBConfigPath}/")
    @Produces("application/json")
    public GenericEntity<List<Form>> getForms(@PathParam("DBConfigPath")String DBConfigPath){
    	FormManager formManager = new FormManager(DBConfigPath);
    	return new GenericEntity<List<Form>>(formManager.getForms()){};
	  }
	
	@GET
    @Path("/form/{DBConfigPath}/id/{formID}")
    @Produces("application/json")
    public Form getForm(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("formID")String formID){
    	FormManager formManager = new FormManager(DBConfigPath);
    	return formManager.getForm(Integer.parseInt(formID));
	  }
	
	@POST
    @Path("/{DBConfigPath}/form/delete/")
    @Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
    public String deleteForm(Form data, @PathParam("DBConfigPath")String DBConfigPath){//, 

		
		FormManager formManager = new FormManager(DBConfigPath);
		System.out.println("####"+data.getFormId().toString()+"####");
    	if(formManager.deleteForm(data.getFormId().toString()))
    	{
    		return "{\"success\":\"true\"}";
    	}
    	else
    	{
    		return "{\"failure\":\"false\"}";
    	}
	  }
	
	
	@Path("/{DBConfigPath}/forms/insertForm")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("text/plain")
	 public String addForm(Form data, @PathParam("DBConfigPath")String DBConfigPath)  {      
	  try{  
	   FormManager formManager = new FormManager(DBConfigPath);
	   return formManager.addForm(data).toString();
	     }
	     	catch(Exception e){
	     	return "{\"failed\":\"false\"}";
	     }   
	 }
	
}
