package charity.dao.managers;

import java.util.List;

import shared.dao.util.ConnectionManager;

import charity.model.FieldType;


public class FieldTypeManager {
	private ConnectionManager conn;
	public FieldTypeManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	public FieldType getFieldType(Integer id){
		FieldType fieldType = (FieldType)conn.get(FieldType.class,id);
		return fieldType;
	}
	
	public List<FieldType> retrieve(){
		List<FieldType> fieldTypes = (List<FieldType>) conn.getTable("FieldType");
		return fieldTypes;
	}
	
	public Integer addFieldType(FieldType fieldType){
		return (Integer) conn.transaction("save",fieldType);	
	}
}
