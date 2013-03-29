package charity.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;
import java.util.HashSet;

@Entity
@Audited
@XmlRootElement(name = "form")
public class Form {
	private Integer formId;
	private FormType formType;
	private String formName;
	private Date dateCreated;
	private String url;
	private Boolean isActive;
	private Set<FormFields> fields = new HashSet<FormFields>();
	
	public Form(){}
	public Form(FormType ft) {
		this.formType = ft;
		this.dateCreated = new Date(Calendar.DATE);
		this.setIsActive(true);
	}	
	
	@XmlElement
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	@XmlElement(name = "formType")
	public FormType getFormType() {
		return formType;
	}
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	@XmlElement
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	@XmlElement
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@XmlElement
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@XmlElement
	public Set<FormFields> getFields() {
		return fields;
	}
	public void setFields(Set<FormFields> fields) {
		this.fields = fields;
	}
	
}
