package charity.dao.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.dao.util.ConnectionManager;
import charity.model.Form;
import charity.model.FormPermissions;
import charity.model.FormPermissionsPK;
import charity.model.UserType;



public class FormPermissionsManager {
	private ConnectionManager conn;
	private String DBConfname;
	
	public FormPermissionsManager(String DBConfname){
		this.DBConfname = DBConfname;
		conn = new ConnectionManager(DBConfname);
	}
	
	public List<Integer> getFormPermissions(Integer userTypeId){
		List<FormPermissions> formIDs = (List<FormPermissions>) conn.getTable("FormPermissions where isActive = 1 and User_Type_Id = "+userTypeId);
		Iterator<FormPermissions> formPermissions_iter = formIDs.iterator();
		List<Integer> results = new ArrayList<Integer>();
		
		while(formPermissions_iter.hasNext()){
			FormPermissions formPermissionsTemp = formPermissions_iter.next();
			
			Integer formId = formPermissionsTemp.getPk().getForm().getFormId();
			results.add(formId);
			
		}
		return results;
	}

	
	public Boolean postFormPermissions(Integer userTypeId, Integer FormId){
		try
		 {
			FormPermissionsPK frmPermissionsPK = new FormPermissionsPK();
			
			FormPermissions frmPermissions = new FormPermissions();
			
			FormManager formManager = new FormManager(this.DBConfname);
			Form theForm = (Form)formManager.getForm(new Integer(FormId));
			
			UserTypeManager userTypeManager = new UserTypeManager(this.DBConfname);
			UserType theUserType = (UserType)userTypeManager.getUserType(new Integer(userTypeId));
			
			frmPermissionsPK.setForm(theForm);
			frmPermissionsPK.setUser_type(theUserType);
			
			frmPermissions.setIsActive(true);
			frmPermissions.setPk(frmPermissionsPK);
			conn.transaction("save",frmPermissions);
			
			return true;
		 }catch (Exception ex)
		 {
			 return false;
		 }
		
	}
}
