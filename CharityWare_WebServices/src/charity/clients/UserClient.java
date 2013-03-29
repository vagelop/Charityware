package charity.clients;

import java.util.List;
import java.util.Map;

import charity.model.User;
import javax.ws.rs.core.MediaType;
import shared.clients.util.WebClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class UserClient {
	private WebClient webClient;
	private ClientResponse clientresponse;
	public UserClient(){
		this.webClient = new WebClient("RESTCharity/userService");
	}
	public User get(String parameter){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return this.clientresponse.getEntity(User.class);
	}
	public String getCharityUsersRegistrationActivity(String DBConfig){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/stats/CharityUsersRegistrationActivity/").path(DBConfig).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<String>(){});
	}
//	public static Map<Integer,List<String>> getForms(){
//		ClientConfig clientConfig = new DefaultClientConfig();
//		clientConfig.getClasses().add(JacksonJsonProvider.class);
//		Client client = Client.create(clientConfig);
//		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/json/users/forms/").
//				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
//				get(ClientResponse.class);
//		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
//	}
	
	public Map<Integer,List<String>> getForms(String DBConfig){
		this.clientresponse = webClient.getClient()
				.resource(webClient.getRestServiceURLPath())
				.path("/charityConfig/").path(DBConfig).path("/users/forms/")
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
	public Map<Integer,Map<Integer,List<String>>> getFormEntities(String username){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
				.path("/json/users/formEntities/").path(username)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,Map<Integer,List<String>>>>(){});
	}
	
	public String getCharityActiveUsers(String DBConfig){
		this.clientresponse = webClient.getClient().resource(webClient.getRestServiceURLPath())
		.path("/stats/ActiveUsers").path(DBConfig).accept(MediaType.APPLICATION_JSON)
		.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return this.clientresponse.getEntity(new GenericType<String>(){});		
	}

}
