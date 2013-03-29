package charity.clients;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class EventClient {
	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public EventClient(){
		this.webClient = new WebClient("RESTCharity/eventService");
	}
	public Map<Integer,List<String>> getEvents(String DBConfig){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/charityConfig/").path(DBConfig).path("/events/")
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
}
