package charity.clients;

import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class FieldSelectionClient {

	private WebClient webClient;
	private ClientResponse clientresponse;
	public FieldSelectionClient(){
		this.webClient = new WebClient("RESTCharity/fieldSelectionService");
	}

	public ArrayList<String> getEvents(Integer field_id){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath()).path("/json/selectionValues/"+field_id).
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<ArrayList<String>>(){});
	}

}
