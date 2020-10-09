package com.mtto.sat.result;

import java.util.Optional;

import com.mtto.sat.modelo.Role;

public class AnsRolOpc {

	private String cr;
	private String descripcion;
	private Optional<Role> roles;
	
	public AnsRolOpc() {
		this.cr = cr;
		this.descripcion = descripcion;
		this.roles = roles;
	}

	public AnsRolOpc(String cr, String descripcion, Optional<Role> roles) {
		this.cr = cr;
		this.descripcion = descripcion;
		this.roles = roles;
	}

	public String getCr() {
		return cr;
	}
	public void setCr(String cr) {
		this.cr = cr;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Optional<Role> getRoles() {
		return roles;
	}
	public void setRoles(Optional<Role> roles) {
		this.roles = roles;
	}
	
	
}
