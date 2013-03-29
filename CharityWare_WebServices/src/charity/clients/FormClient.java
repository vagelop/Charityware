package charity.clients;

import charity.model.Form;

import java.util.List;
import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class FormClient {
	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public FormClient(){
		this.webClient = new WebClient("RESTCharity/formService");
	}
	public List<Form> getForms(String DBConfig){
		this.clientresponse = this.webClient.getClient().resource(this.webClient.getRestServiceURLPath()).path("/forms/").path(DBConfig).
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<List<Form>>(){});
	}	
	public Form getForm(String DBConfig, Integer id){
		this.clientresponse = this.webClient.getClient().resource(this.webClient.getRestServiceURLPath()).path("/form/").path(DBConfig).path("/id/"+id).
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Form>(){});
	}
}
