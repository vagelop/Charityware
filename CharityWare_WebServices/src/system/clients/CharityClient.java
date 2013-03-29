package system.clients;

import shared.clients.util.WebClient;
import system.model.Charity;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class CharityClient {	
	private WebClient webClient;
	private ClientResponse clientresponse;
	public CharityClient(){
		this.webClient = new WebClient("RESTSystem/charityService");
	}
	public Map<Integer,List<String>> getCharitiesRequests(){		
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/charityApprovals").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});	
	}

	public Charity getUserCharity(int userId){
		String uID = Integer.toString(userId);
		this.clientresponse = webClient.getClient()
				.resource(webClient.getRestServiceURLPath())
				.path("/charity/user/").path(uID).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(Charity.class);	
	}

	public void addCharity(Charity charity){
		this.webClient.getClient().resource(webClient.getRestServiceURLPath())
		.path("/addCharity").type(MediaType.APPLICATION_JSON)
		.post(charity);
	}

	public void addCharities(List<Charity> charities){
		this.webClient.getClient().resource(webClient.getRestServiceURLPath())
		.path("/addCharities").type(MediaType.APPLICATION_JSON)
		.post(charities);
	}
	public String getSystemVerifiedCharities(){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/stats/VerifiedCharities").type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<String>(){});

	}
	public String getCharityRegistrationActivity(){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/stats/CharityRegistrationActivity").type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<String>(){});		
	}
}
