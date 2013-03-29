package env.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.webviewprototype.facade.impl.DBManager;

public class DataBean {

	private DBManager manager;
	private User user;
	private List<String> formNames;
	private String selectedForm;
	private List<FormFields> formFields;
	private Boolean created=false;
	private List<FilledForm> filledForm;
	private Charity selectedCharity;
	
	
	public void flush() {
		this.manager = null;
		this.user = new User();
		this.formNames = new LinkedList<String>();
		this.selectedForm = "";
		this.formFields = new LinkedList<FormFields>();
		this.created=false;
		this.filledForm = new ArrayList<FilledForm>();
		this.selectedCharity = new Charity();
	}
	public Charity getSelectedCharity() {
		return selectedCharity;
	}

	public void setSelectedCharity(Charity selectedCharity) {
		this.selectedCharity = selectedCharity;
	}

	public List<FilledForm> getFilledForm() {
		return filledForm;
	}

	public void setFilledForm(List<FilledForm> filledForm) {
		this.filledForm = filledForm;
	}

	private List<Form> allForms;
	

	public List<Form> getAllForms() {
		return allForms;
	}

	public void setAllForms(List<Form> allForms) {
		this.allForms = allForms;
	}

	public Boolean getCreated() {
		return created;
	}

	public String getSelectedForm() {
		return selectedForm;
	}

	public void setSelectedForm(String selectedForm) {
		this.selectedForm = selectedForm;
	}
	
	public List<String> getFormNames() {
		return formNames;
	}

	public void setFormNames(List<String> formNames) {
		this.formNames = formNames;
	}


	public List<FormFields> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormFields> formFields) {
		this.formFields = formFields;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	private static final DataBean databean = new DataBean();
	
	
	private DataBean() {
			this.filledForm = new LinkedList<FilledForm>();
	}



	public DBManager getManager() {
		return manager;
	}
	

	public void setManager(DBManager manager) {
		this.manager = manager;
	}
	
	public static DataBean getDataBean() {
		return databean;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
