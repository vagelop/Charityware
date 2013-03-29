package env.Entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class FilledForm {
	
	private Integer filled_form_id;
	private FormFields formFields;
	private String value;
	private User user;
	private Integer record_id;
	private Boolean isActive;
	
	
	public FilledForm(){}
	public FilledForm(User user, FormFields form_field_id){
		this.formFields=form_field_id;
		this.user = user;
		this.record_id=new Integer(1);
		this.isActive=true;
	}
	

	public Integer getFilled_form_id() {
		return filled_form_id;
	}
	public void setFilled_form_id(Integer filled_form_id) {
		this.filled_form_id = filled_form_id;
	}
	

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user_id) {
		this.user = user_id;
	}

	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public FormFields getFormFields() {
		return formFields;
	}
	public void setFormFields(FormFields form_field_id) {
		this.formFields = form_field_id;
	}
	
	
	
	/*@XmlElement
	public List<FormFields> getForm_fields() {
		return form_fields;
	}
	public void setForm_fields(List<FormFields> form_fields) {
		this.form_fields = form_fields;
	}*/
}
