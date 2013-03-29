package system.clients;

import shared.clients.util.WebClient;
import system.model.User;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class UserClient {
	private WebClient webClient;
	private ClientResponse clientresponse;
	public UserClient(){
		this.webClient = new WebClient("RESTSystem/userService");
	}
	public User get(String parameter){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(User.class);
	}	

	public String getSystemActiveUsers(){
		this.clientresponse = this.webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/stats/ActiveUsers").type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<String>(){});

	}
	public String getUsersRegistrationActivity(){
		this.clientresponse = this.webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/stats/UsersRegistrationActivity").type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<String>(){});
	}
}