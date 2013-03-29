package charity.services;

import charity.dao.managers.FormFieldsManager;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;


@Path("/formFieldsService")
public class FormFieldsService {
	
	/*@GET
	@Path("/{DBConfigPath}/formFields/{formId}")
	@Produces("application/json")
	public GenericEntity<Map<Integer,List<String>>> JSONformFieldsretrieve(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("formId")Integer formId){
		FormFieldsManager formFieldsManager = new FormFieldsManager(DBConfigPath);
		Map<Integer,List<String>> map= formFieldsManager.retrieve(formId);
		GenericEntity<Map<Integer, List<String>>> entity = new GenericEntity<Map<Integer, List<String>>>(map){};
		return entity;
		//return map;
	}*/
	
	
	@GET
	@Path("/{DBConfigPath}/formFields/")
	@Produces("application/json")
	public GenericEntity<Map<Integer,String>> getAllFormFields(@PathParam("DBConfigPath")String DBConfigPath){
		FormFieldsManager formFieldsManager = new FormFieldsManager(DBConfigPath);
		Map<Integer,String> map = formFieldsManager.getListFormFields();
		GenericEntity<Map<Integer, String>> entity = new GenericEntity<Map<Integer,String>>(map){};
		return entity;
	}
	
	/*@GET
	@Path("/formFields/{formId}")
	@Produces(MediaType.APPLICATION_XML)
	public JResponse<GenericEntity<Map<Integer,List<String>>>> formFieldsretrieve(@PathParam("formId")Integer formId){
		Map<Integer,List<String>> map= FormFieldsManager.retrieve(formId);
		GenericEntity<Map<Integer, List<String>>> entity = new GenericEntity<Map<Integer, List<String>>>(map){};
		return JResponse.ok(entity).build();
		//return entity;
	}*/

}
