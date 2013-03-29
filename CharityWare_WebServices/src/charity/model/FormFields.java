package charity.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "formFields")
public class FormFields 
{
	private Integer f_id;
	private String field_label;
	private FieldType fieldType;
	private Set<FieldSelection> field_selections = new HashSet<FieldSelection>();
	private Float X_coordinate;
	private Float Y_coordinate;
	private Boolean isRequired;
	private Boolean default_value;
	private Float minValue;
	private Float maxValue;
	private String creator_name;
	private Boolean isActive;
	private Date date_created;
	
	private Set<FilledForm> filledForms = new HashSet<FilledForm>();
	
	@XmlElement
	public String getField_label() {
		return field_label;
	}
	public void setField_label(String field_label) {
		this.field_label = field_label;
	}
			
	@XmlElement
	public Integer getF_id() {
		return f_id;
	}
	public void setF_id(Integer f_id) {
		this.f_id = f_id;
	}
	
	@XmlElement(name = "fieldType")
	public FieldType getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	
	
	@XmlElement
	public Set<FieldSelection> getField_selections() {
		return field_selections;
	}
	public void setField_selections(Set<FieldSelection> fieldselections) {
		this.field_selections = fieldselections;
	}
	
	@XmlElement
	public Float getX_coordinate() {
		return X_coordinate;
	}
	public void setX_coordinate(Float x_coordinate) {
		X_coordinate = x_coordinate;
	}
	@XmlElement
	public Float getY_coordinate() {
		return Y_coordinate;
	}
	public void setY_coordinate(Float y_coordinate) {
		Y_coordinate = y_coordinate;
	}
	@XmlElement
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	@XmlElement
	public Boolean getDefault_value() {
		return default_value;
	}
	public void setDefault_value(Boolean default_value) {
		this.default_value = default_value;
	}
	@XmlElement
	public Float getMinValue() {
		return minValue;
	}
	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}
	@XmlElement
	public Float getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}
	
	@XmlElement
	public String getCreator_name() {
		return creator_name;
	}
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@XmlElement
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	@XmlElement
	public Set<FilledForm> getFilledForms() {
		return this.filledForms;
	}
	public void setFilledForms(Set<FilledForm> s) {
		filledForms = s;
	}
}
