package charity.model;


import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "fieldType")
public class FieldType {
		
	private Integer fieldType_id;
	private String field_type;
	private String field_dataType;
	private String field_Description;
	private Boolean isActive;	
	
	public FieldType(){}
	
	public FieldType (String type) {
		this.field_type=type;
		this.isActive=true;
	}
	public FieldType (String type,String field_dataType, String fieldDescription) {
		this.field_type=type;
		this.field_dataType = field_dataType;
		this.field_Description = fieldDescription;
		this.isActive=true;
	}
	@XmlElement
	public Integer getFieldType_id() {
		return fieldType_id;
	}
	public void setFieldType_id(Integer field_type_id) {
		this.fieldType_id = field_type_id;
	}
	@XmlElement
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	@XmlElement
	public String getField_dataType() {
		return field_dataType;
	}
	public void setField_dataType(String field_dataType) {
		this.field_dataType = field_dataType;
	}
	@XmlElement
	public String getField_Description() {
		return field_Description;
	}
	public void setField_Description(String field_Description) {
		this.field_Description = field_Description;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
