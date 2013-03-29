package env.Entities;

import java.util.LinkedList;
import java.util.List;

public class AndroidDropDownField extends AndroidField {

	private List<FieldSelection> dropDownValues;
	
	public AndroidDropDownField() {
		this.dropDownValues  = new LinkedList<FieldSelection>();
	}

	public List<FieldSelection> getDropDownValues() {
		return dropDownValues;
	}

	public void setDropDownValues(List<FieldSelection> dropDownValues) {
		this.dropDownValues = dropDownValues;
	}
	
	

}
