package charity.services;

import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import charity.dao.managers.EventManager;

@Path("/eventService")
public class EventService {
	 @GET
	 @Path("/charityConfig/{DBConfigPath}/events")
	 @Produces("application/json")
	 public Map<Integer,ArrayList<String>> geEventsJSON(@PathParam("DBConfigPath")String DBConfigPath){
		 	EventManager eventManager = new EventManager(DBConfigPath);
	    	return eventManager.getEvents();       
	    }
}
