package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(RolePermissionId.class)
@Table(name = "m_role_permission")
public class RolePermission {
	
	@Id	
	@Column(name = "role_id")	
	private Integer roleId;
	
	@Id	
	@Column(name = "permission_id")	
	private Integer permissionId;

	
	public RolePermission() {

		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	
	public RolePermission(Integer roleId, Integer permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
