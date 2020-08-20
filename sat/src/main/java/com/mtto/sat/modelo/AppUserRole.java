package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(AppUserRoleId.class)
@Table(name = "m_appuser_role")
public class AppUserRole {

	@Id
	@Column(name = "appuser_id")	
	private Integer appUserId; 
	
	@Id
	@Column(name = "role_id", nullable = false)	
	private Integer roleId;

	public AppUserRole() {
		this.appUserId = appUserId;
		this.roleId = roleId;
	}
	
	public AppUserRole(Integer appUserId, Integer roleId) {
		super();
		this.appUserId = appUserId;
		this.roleId = roleId;
	}

	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	


}
