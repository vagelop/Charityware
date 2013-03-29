package env.Entities;

import java.sql.Date; 
import java.sql.Timestamp;
import java.util.Set;

public class FormFields {
	private Integer f_id;
	private String field_label;
	private FieldType field_type;
	private Set<FieldSelection> field_selections;
	private Float X_coordinate;
	private Float Y_coordinate;
	private Boolean isRequired;
	private Boolean default_value;
	private Float minValue;
	private Float maxValue;
	private Boolean isActive;
	private Date date_created;
	private Form form;
	private String creatorName;
	

	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public void setField_selections(Set<FieldSelection> field_selections) {
		this.field_selections = field_selections;
	}



	public String getField_label() {
		return field_label;
	}

	public Integer getF_id() {
		return f_id;
	}
	public void setF_id(Integer f_id) {
		this.f_id = f_id;
	}
	public void setField_label(String field_label) {
		this.field_label = field_label;
	}



	public FieldType getField_type() {
		return field_type;
	}
	public void setField_type(FieldType field_type) {
		this.field_type = field_type;
	}
	public Set<FieldSelection> getField_selections() {
		return field_selections;
	}
	public void setField_selection_Id(Set<FieldSelection> field_selections) {
		this.field_selections = field_selections;
	}

	public Float getX_coordinate() {
		return X_coordinate;
	}
	public void setX_coordinate(Float x_coordinate) {
		X_coordinate = x_coordinate;
	}

	public Float getY_coordinate() {
		return Y_coordinate;
	}
	public void setY_coordinate(Float y_coordinate) {
		Y_coordinate = y_coordinate;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Boolean getDefault_value() {
		return default_value;
	}
	public void setDefault_value(Boolean default_value) {
		this.default_value = default_value;
	}

	public Float getMinValue() {
		return minValue;
	}
	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}

	public Float getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}


	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
/*	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
*/
	
	
}
