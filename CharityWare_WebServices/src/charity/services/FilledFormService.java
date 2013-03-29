package charity.services;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import charity.dao.managers.FilledFormManager;
import charity.model.FilledForm;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Path("/filledFormService")
public class FilledFormService {
	
	@GET
    @Path("/{DBConfigPath}/filledforms/records")
    @Produces("application/json")
    public GenericEntity<String> getRecordsDataJSON(@PathParam("DBConfigPath")String DBConfigPath){
		FilledFormManager filledFormManager = new FilledFormManager(DBConfigPath);
    	GenericEntity<String> entity = new GenericEntity<String>(filledFormManager.getRecordsData()){};
    	return entity;       
    }
	
	 @Path("/{DBConfigPath}/filledforms/insertFilledForms")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("text/plain")
	 public String addFilledForms(@PathParam("DBConfigPath")String DBConfigPath,String data)  {      
		try{  
			data =data.split("=")[1];
			data=URLDecoder.decode(data);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();
			Type listType = new TypeToken<List<FilledForm>>() {}.getType();
			List<FilledForm> filledForms = gson.fromJson(data, listType);
			FilledFormManager filledFormManager = new FilledFormManager(DBConfigPath);
			return filledFormManager.addFilledForms(filledForms).toString();
	    }
	    catch(Exception e){
	    	return "0";
	    }   
	}
	
	@GET
    @Path("/{DBConfigPath}/filledforms/getSearchResults/{FieldLabel}/{Criteria}")
    @Produces("application/json")
	public 	Map<Integer,ArrayList<String>> getSearchResults(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("FieldLabel")String FieldLabel,@PathParam("Criteria")String Criteria){
		
			FilledFormManager filledFormManager = new FilledFormManager(DBConfigPath);
			Map<Integer,ArrayList<String>> resultSet = (Map<Integer,ArrayList<String>>)filledFormManager.getSearchResults(FieldLabel,Criteria);
	    	return resultSet;  	
	} 

}
