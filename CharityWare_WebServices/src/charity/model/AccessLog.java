package charity.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "accesslog")
public class AccessLog {

	private Integer access_log_id;
	private Form form;
	private Set<FormFields> fields;
	private User user;
	private Date access_start_date;
	private Date access_end_date;
	private String device;
	private boolean isOnline;
	private String location;
	
	public AccessLog() {}
	public AccessLog(Date start, User user){
		this.access_start_date=start;
		this.user=user;
	}
	
	@XmlElement
	public Integer getAccess_log_id() {
		return access_log_id;
	}
	public void setAccess_log_id(Integer access_log_id) {
		this.access_log_id = access_log_id;
	}
	
	@XmlElement
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	
	@XmlElement
	public Set<FormFields> getFields() {
		return fields;
	}
	public void setFields(Set<FormFields> fields) {
		this.fields = fields;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlElement
	public Date getAccess_start_date() {
		return access_start_date;
	}
	public void setAccess_start_date(Date access_start_date) {
		this.access_start_date = access_start_date;
	}
	
	@XmlElement
	public Date getAccess_end_date() {
		return access_end_date;
	}
	public void setAccess_end_date(Date access_end_date) {
		this.access_end_date = access_end_date;
	}
	
	@XmlElement
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
	@XmlElement
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlElement
	public boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
}
