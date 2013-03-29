package charity.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formpermissions")
public class FormPermissionsPK implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -147082215393721075L;
	private Form form;
	private UserType user_type;
	public FormPermissionsPK() {}
	
	
	@XmlElement
	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	@XmlElement
	public UserType getUser_type() {
		return user_type;
	}

	public void setUser_type(UserType user_type) {
		this.user_type = user_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
