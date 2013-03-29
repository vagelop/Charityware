package charity.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "formpermissions")
public class FormPermissions {
	
	private	FormPermissionsPK pk;
	private String permission;
	private Boolean isActive;
	
	@XmlElement	
	public FormPermissionsPK getPk() {
		return pk;
	}
	public void setPk(FormPermissionsPK pk) {
		this.pk = pk;
	}
	
	@XmlElement
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
