package charity.dao.managers;

import shared.dao.util.ConnectionManager;
import charity.model.FormType;

public class FormTypeManager {
	private ConnectionManager conn;
	public FormTypeManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	
	public FormType getFormType(Integer formTypeId){
		FormType formType = (FormType)conn.get(FormType.class,formTypeId);
		return formType;
	}
}
