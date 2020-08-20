package Respuesta;

import java.util.List;

import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.RolePermission;

public class AnswAuth {

//UsuarioRol
	private Integer UserId; 
	
//Usuario
//	private int id; 
//	private int isDeleted; 
//	private int staffId; 
	private String username; 
//	private String firstname; 
//	private String lastname; 
	private String base64EncodedAuthenticationKey; 
	private boolean authenticated;
	private int officeId;

//	private String email; 
//	private boolean firsttimeLoginRemaining; 
//	private boolean nonexpired; 
	private boolean nonlocked; 
//	private boolean nonexpiredCredentials; 
//	private boolean enabled; 
	//	private Date lastTimePasswordUpdated; 
//	private boolean passwordNeverExpires; 
//	private boolean isSelfServiceUser;
//	
//UsuarioRol
//	private Integer UserId; 
	private List<AppUserRole> RoleId;

// PERMISSION	
	private List<RolePermission> RolePerms; 
//ROL
	private String name; 
	private String description; 
	private boolean isDisable;
//	
//************************************************************* GETTERS/SETTERS
//	
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBase64EncodedAuthenticationKey() {
		return base64EncodedAuthenticationKey;
	}
	public void setBase64EncodedAuthenticationKey(String base64EncodedAuthenticationKey) {
		this.base64EncodedAuthenticationKey = base64EncodedAuthenticationKey;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public boolean isNonlocked() {
		return nonlocked;
	}
	public void setNonlocked(boolean nonlocked) {
		this.nonlocked = nonlocked;
	}
	public List<AppUserRole> getRoleId() {
		return RoleId;
	}
	public void setRoleId(List<AppUserRole> roleId) {
		RoleId = roleId;
	}
	public List<RolePermission> getRolePerms() {
		return RolePerms;
	}
	public void setRolePerms(List<RolePermission> rolePerms) {
		RolePerms = rolePerms;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDisable() {
		return isDisable;
	}
	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	

}
