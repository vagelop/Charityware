package charity.dao.managers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import shared.dao.util.ConnectionManager;
import charity.model.Form;
import charity.model.FormFields;

public class FormFieldsManager {
	private ConnectionManager conn;
	private String DBConfname;
	public FormFieldsManager(String DBConfname){
		//this.DBConfname = DBConfname;
		System.out.println("HELLLOOO!");
		this.DBConfname = DBConfname;
		conn = new ConnectionManager(DBConfname);
		//conn.setDBConfname(DBConfname);
	}
	/*public  Map<Integer,List<String>> retrieve(Integer form_id){
		List<FormFields> formFields = (List<FormFields>) conn.getTable("FormFields");
		Iterator<FormFields> iter = formFields.iterator();
		Map<Integer,List<String>> results = new TreeMap<Integer,List<String>>();
		while(iter.hasNext()){
			FormFields formFieldsTemp = iter.next();
			if (formFieldsTemp.getForm().getFormId().equals(form_id))
			{
				FieldTypeManager fieldTypeManager = new FieldTypeManager(this.DBConfname);
				FieldType fieldTypeTemp= fieldTypeManager.getFieldType(formFieldsTemp.getField_type().getField_type_id());
				ArrayList<String> dataArray = new ArrayList<String>();
				dataArray.add(formFieldsTemp.getField_label());
				dataArray.add(fieldTypeTemp.getField_type());
				dataArray.add(fieldTypeTemp.getField_dataType());
				dataArray.add(formFieldsTemp.getIsRequired().toString());
				results.put(formFieldsTemp.getF_id(), dataArray);
			}
		}
		return results;
	}*/
	
	public  Map<Integer,String> getListFormFields(){
		
		List<FormFields> formFields = (List<FormFields>) conn.getTable("FormFields where isActive = 1");
		Iterator<FormFields> formFields_iter = formFields.iterator();
		Map<Integer,String> results = new TreeMap<Integer,String>();
		
		while(formFields_iter.hasNext()){
				FormFields formFieldsTemp = formFields_iter.next();
			
				String field = formFieldsTemp.getField_label();
				results.put(formFieldsTemp.getF_id(), field);
		}
		return results;
	}
	
	
	public FormFields getFormFields(Integer id){
		
		FormFields formFields = (FormFields)conn.get(FormFields.class,id);
		return formFields;
	}
	
	public List<FormFields> getFormFields(String formId){	
		//List<FormFields> ret = new ArrayList<FormFields>();
		FormManager formManager = new FormManager(this.DBConfname);
		Form theForm = (Form)formManager.getForm(new Integer(formId));
		//List tForm = session.createCriteria(FormFields.class)
			//	.add(Restrictions.eq("form_id", theForm)).list();
		List<FormFields> tForm = (List<FormFields>)conn.searchCriteria(FormFields.class,"form_id",theForm);
		/*for(Object o : tForm)
        {
       	 	ret.add((FormFields)o);
        } */
		return tForm;
	}
/*
 * select B.Field_Id, B.Field_Label,C.Field_Type, C.Field_DataType, B.isRequired 
 * FROM Form A INNER JOIN Form_Fields B ON A.Form_Id = B.Form_Id
 * INNER JOIN Field_Type C ON B.Field_Type_Id = C.Field_Type_Id
 * WHERE A.isActive = 1 AND B.isActive = 1 AND C.isActive = 1 AND A.Form_Id = 1
 */
}
