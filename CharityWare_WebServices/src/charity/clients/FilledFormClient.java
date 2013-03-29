package charity.clients;


import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class FilledFormClient {	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public FilledFormClient(){
		this.webClient = new WebClient("RESTCharity/filledFormService");
	}
	public String getRecordsData(String DBConfig){
		this.clientresponse = this.webClient.getClient().resource(this.webClient.getRestServiceURLPath())
				.path("/"+DBConfig+"/filledforms/records")
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<String>(){});
	}

}
