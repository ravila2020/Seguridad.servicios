package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "m_appuser")
public class AppUser {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Column(name = "is_deleted")	
	private int isDeleted; 
	@Column(name = "office_id")	
	private int officeId;
	@Column(name = "staff_id")	
	private int staffId; 
	@Column(name = "username")
	private String username; 
	@Column(name = "firstname", nullable = false, length = 100)	
	private String firstname; 
	@Column(name = "lastname", nullable = false, length = 100)	
	private String lastname; 
	@Column(name = "password", nullable = false, length = 255)	
	private String password; 
	@Column(name = "email", nullable = false, length = 100)	
	private String email; 
	@Column(name = "firsttime_login_remaining", nullable = false)	
	private boolean firsttimeLoginRemaining; 
	@Column(name = "nonexpired", nullable = false)	
	private boolean nonexpired; 
	@Column(name = "nonlocked", nullable = false)	
	private boolean nonlocked; 
	@Column(name = "nonexpired_credentials", nullable = false)	
	private boolean nonexpiredCredentials; 
	@Column(name = "enabled", nullable = false)	
	private boolean enabled; 
	@Column(name = "last_time_password_updated")	
	private Date lastTimePasswordUpdated; 
	@Column(name = "password_never_expires", nullable = false)	
	private boolean passwordNeverExpires; 
	@Column(name = "is_self_service_user", nullable = false)	
	private boolean isSelfServiceUser;

	
	public AppUser() {
		this.id = id;
		this.isDeleted = isDeleted;
		this.officeId = officeId;
		this.staffId = staffId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.firsttimeLoginRemaining = firsttimeLoginRemaining;
		this.nonexpired = nonexpired;
		this.nonlocked = nonlocked;
		this.nonexpiredCredentials = nonexpiredCredentials;
		this.enabled = enabled;
		this.lastTimePasswordUpdated = lastTimePasswordUpdated;
		this.passwordNeverExpires = passwordNeverExpires;
		this.isSelfServiceUser = isSelfServiceUser;
	}
		
	public AppUser(Integer id, int isDeleted, int officeId, int staffId, String username, String firstname, String lastname,
			String password, String email, boolean firsttimeLoginRemaining, boolean nonexpired, boolean nonlocked,
			boolean nonexpiredCredentials, boolean enabled, Date lastTimePasswordUpdated, boolean passwordNeverExpires,
			boolean isSelfServiceUser) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.officeId = officeId;
		this.staffId = staffId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.firsttimeLoginRemaining = firsttimeLoginRemaining;
		this.nonexpired = nonexpired;
		this.nonlocked = nonlocked;
		this.nonexpiredCredentials = nonexpiredCredentials;
		this.enabled = enabled;
		this.lastTimePasswordUpdated = lastTimePasswordUpdated;
		this.passwordNeverExpires = passwordNeverExpires;
		this.isSelfServiceUser = isSelfServiceUser;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isFirsttimeLoginRemaining() {
		return firsttimeLoginRemaining;
	}
	public void setFirsttimeLoginRemaining(boolean firsttimeLoginRemaining) {
		this.firsttimeLoginRemaining = firsttimeLoginRemaining;
	}
	public boolean isNonexpired() {
		return nonexpired;
	}
	public void setNonexpired(boolean nonexpired) {
		this.nonexpired = nonexpired;
	}
	public boolean isNonlocked() {
		return nonlocked;
	}
	public void setNonlocked(boolean nonlocked) {
		this.nonlocked = nonlocked;
	}
	public boolean isNonexpiredCredentials() {
		return nonexpiredCredentials;
	}
	public void setNonexpiredCredentials(boolean nonexpiredCredentials) {
		this.nonexpiredCredentials = nonexpiredCredentials;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getLastTimePasswordUpdated() {
		return lastTimePasswordUpdated;
	}
	public void setLastTimePasswordUpdated(Date lastTimePasswordUpdated) {
		this.lastTimePasswordUpdated = lastTimePasswordUpdated;
	}
	public boolean isPasswordNeverExpires() {
		return passwordNeverExpires;
	}
	public void setPasswordNeverExpires(boolean passwordNeverExpires) {
		this.passwordNeverExpires = passwordNeverExpires;
	}
	public boolean isSelfServiceUser() {
		return isSelfServiceUser;
	}
	public void setSelfServiceUser(boolean isSelfServiceUser) {
		this.isSelfServiceUser = isSelfServiceUser;
	}


	
}
