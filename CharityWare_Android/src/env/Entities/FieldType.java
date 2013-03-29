package env.Entities;

import java.sql.Timestamp;
import java.util.Calendar;



public class FieldType {
		
	private Integer field_type_id;
	private String field_type;
	private String field_dataType;
	private String field_Description;
	private Boolean isActive;
	
	
	
	public FieldType(){}
	
	public FieldType (String type) {
		this.field_type=type;
		this.isActive=true;
	}

	public Integer getField_type_id() {
		return field_type_id;
	}
	public void setField_type_id(Integer field_type_id) {
		this.field_type_id = field_type_id;
	}

	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}

	public String getField_dataType() {
		return field_dataType;
	}
	public void setField_dataType(String field_dataType) {
		this.field_dataType = field_dataType;
	}

	public String getField_Description() {
		return field_Description;
	}
	public void setField_Description(String field_Description) {
		this.field_Description = field_Description;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
}
