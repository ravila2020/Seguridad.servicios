package com.mtto.sat.result;

import java.util.List;

import com.mtto.sat.modelo.Role;

public class AnsRol {

	private String cr;
	private String descripcion;
	private List<Role> roles;
	
	public AnsRol(String cr, String descripcion, List<Role> roles) {
		this.cr = cr;
		this.descripcion = descripcion;
		this.roles = roles;
	}
	
	public AnsRol() {
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
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
