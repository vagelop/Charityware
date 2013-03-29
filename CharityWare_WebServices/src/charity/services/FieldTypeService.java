package charity.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import charity.dao.managers.FieldTypeManager;
import charity.model.FieldType;
@Path("/fieldTypeService")
public class FieldTypeService {
	
	@GET
    @Path("/fieldTypes/{DBConfigPath}/")
    @Produces("application/json")
    public List<FieldType> getFieldTypes(@PathParam("DBConfigPath")String DBConfigPath){
		FieldTypeManager fieldTypeManager = new FieldTypeManager(DBConfigPath);
    	return new ArrayList<FieldType>(fieldTypeManager.retrieve());
	  }
	

}
