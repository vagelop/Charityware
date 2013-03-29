package charity.dao.managers;

import charity.model.FieldSelection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.dao.util.ConnectionManager;

public class FieldSelectionManager {
	private ConnectionManager conn;
	public FieldSelectionManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	public ArrayList<String>getValues(Integer field_id ){
		ArrayList<String> values = new ArrayList<String>();
		List<FieldSelection> fieldSelections = (List<FieldSelection>) conn.getTable("FieldSelection");
		Iterator<FieldSelection> iter = fieldSelections.iterator();
		while(iter.hasNext()){
			FieldSelection cur = iter.next();
			if(cur.getField_selection_id().equals(field_id)){
				values.add(cur.getField_selection_value());
			}
		}
		
		return values;
	}

}
