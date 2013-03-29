package charity.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "filledForm")
public class FilledForm {
	
	private Integer filled_form_id;
	private FormFields formFields;
	private String value;
	private User user;
	private Integer record_id;
	private Boolean isActive;
	
	
	public FilledForm(){}
	public FilledForm(User user, FormFields form_field_id){
		this.user = user;
		this.record_id=new Integer(1);
		this.isActive=true;
	}
	
	@XmlElement
	public Integer getFilled_form_id() {
		return filled_form_id;
	}
	public void setFilled_form_id(Integer filled_form_id) {
		this.filled_form_id = filled_form_id;
	}
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlElement
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@JsonIgnore
	public FormFields getFormFields() {
		return formFields;
	}
	public void setFormFields(FormFields formFields) {
		this.formFields = formFields;
	}
}
