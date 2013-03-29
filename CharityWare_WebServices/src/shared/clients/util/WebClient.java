package shared.clients.util;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WebClient {
	private ClientConfig clientConfig;
	private Client client;
	private String serviceURLPath;
	private final String siteUrl = "http://localhost:8080/CharityWare_Lite/"; 
	 
	public WebClient(String serviceURLPath){
		this.clientConfig = new DefaultClientConfig();
		this.clientConfig.getClasses().add(JacksonJsonProvider.class);
		this.client = Client.create(clientConfig);
		this.serviceURLPath = serviceURLPath;
	}
	
	public Client getClient(){
		return this.client;
	}
	public String getRestServiceURLPath(){
		return this.siteUrl+this.serviceURLPath;
	}
}
