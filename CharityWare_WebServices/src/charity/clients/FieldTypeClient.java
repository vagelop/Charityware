package charity.clients;

import java.util.List;

import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import charity.model.FieldType;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class FieldTypeClient {
	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public FieldTypeClient(){
		this.webClient = new WebClient("RESTCharity/fieldTypeService");
	}
	public List<FieldType> getFieldTypes(String DBConfig){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath()).path("/fieldTypes/").path(DBConfig).
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<List<FieldType>>(){});
	}
}
