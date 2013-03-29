package charity.dao.managers;

import shared.dao.util.ConnectionManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import charity.model.Event;

public class EventManager{
	private ConnectionManager conn;
	public EventManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	public Map<Integer,ArrayList<String>> getEvents(){
		List<Event> events = (List<Event>) conn.getTable("Event");
		Iterator<Event> iter = events.iterator();
		Map<Integer,ArrayList<String>> results = new TreeMap<Integer,ArrayList<String>>();
		while(iter.hasNext()){
			Event event = iter.next();
			if(event.getEvent_date().after(new Date(Calendar.getInstance().getTimeInMillis()))){						
				ArrayList<String> value = new ArrayList<String>();
				value.add(event.getEvent_date().toString());
				value.add(event.getEvent_name());
				value.add(event.getEvent_location());
				value.add(event.getEvent_time().toString());
				value.add(event.getEvent_description());
				results.put(event.getEvent_id(), value);				
			}
		}
		return results;		
	}
}
