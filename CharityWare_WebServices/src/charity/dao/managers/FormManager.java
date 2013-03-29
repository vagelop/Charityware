package charity.dao.managers;


import charity.model.FieldSelection;
import charity.model.FieldType;
import charity.model.Form;
import charity.model.FormFields;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import shared.dao.util.ConnectionManager;

import java.sql.Date;

public class FormManager {
	private ConnectionManager conn;

	public FormManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	
	public List<Form> retrieve(){
		List<Form> forms = (List<Form>) conn.getTable("Form");
		return forms;
	}
	
	public List<Form> getForms(){
		List<Form> forms = (List<Form>) conn.getTable("Form where isActive = 1");
		return forms;
	} 
	
	public Form getForm(Integer id){
		Form form = (Form)conn.get(Form.class,id);
		return form;
	}
	
	public boolean deleteForm(String formId){
		
		Form  killme= (Form)conn.searchCriteria(Form.class,new Integer(formId));
		System.out.println(killme.getFormName());
		/*Form killMe = (Form ) session.createCriteria(Form.class)
                .add(Restrictions.idEq( new Integer(formId))).uniqueResult();*/
		
		killme.setIsActive(false);
		if(killme!=null){
			Serializable serial = conn.transaction("update", killme);
			if(serial!=null)
				return true;
		}
		return false;	
	}
	
	public Integer addForm(Form form)
	{			
		return (Integer) conn.transaction("save",form);		  
	}
}
