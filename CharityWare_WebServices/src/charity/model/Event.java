package charity.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;


@Entity
@Audited
@XmlRootElement(name = "event")
public class Event {
	
	private Integer event_id;
	private String event_name;
	private String event_description;
	private String event_location;
	private Date event_date;
	private Time event_time;
	private User user;
	
	@XmlElement
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	
	@XmlElement
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	@XmlElement
	public String getEvent_description() {
		return event_description;
	}
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
	@XmlElement
	public String getEvent_location() {
		return event_location;
	}
	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}
	@XmlElement
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	@XmlElement
	public Time getEvent_time() {
		return event_time;
	}
	public void setEvent_time(Time event_time) {
		this.event_time = event_time;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
