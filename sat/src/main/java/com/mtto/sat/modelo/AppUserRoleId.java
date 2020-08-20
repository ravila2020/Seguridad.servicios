package com.mtto.sat.modelo;

import java.io.Serializable;

public class AppUserRoleId implements Serializable{

	private Integer appUserId; 
	private Integer roleId;
	
	public AppUserRoleId() {
		this.appUserId = appUserId;
		this.roleId = roleId;
	}
	
	public AppUserRoleId(Integer appUserId, Integer roleId) {
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
