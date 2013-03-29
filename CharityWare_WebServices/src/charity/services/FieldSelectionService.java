package charity.services;

import charity.dao.managers.FieldSelectionManager;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/fieldSelectionService")
public class FieldSelectionService {
	@GET
	@Path("/charityConfig/{DBConfigPath}/selectionValues/{field_id}")
	@Produces("application/json")
	public ArrayList<String> getSelectionValues(@PathParam("field_id")Integer field_id,@PathParam("DBConfigPath")String DBConfigPath){
		FieldSelectionManager fieldSelectionManager = new FieldSelectionManager(DBConfigPath);
		ArrayList<String> values = fieldSelectionManager.getValues(field_id);
		return values;    
	}
}
