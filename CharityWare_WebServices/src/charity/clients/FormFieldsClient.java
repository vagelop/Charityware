package charity.clients;

import charity.model.User;

import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class FormFieldsClient {
	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public FormFieldsClient(){
		this.webClient = new WebClient("RESTCharity/formFieldsService");
	}
	public Map<Integer,List<String>> getData(Integer form_id){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/json/formFields/").path(form_id.toString())
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
	public User get(String parameter){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(User.class);
	}
	
	public Map<Integer,String> getListFormFields(String DBConfig){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path(DBConfig).path("/formFields/")
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<Map<Integer,String>>(){});
	}
	
	

}
