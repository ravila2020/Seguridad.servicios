package com.mtto.sat.result;

import java.util.List;

import com.mtto.sat.modelo.RolePermission;

public class AnsRPList {

	private String cr;
	private String descripcion;
	private List<RolePermission> contenido;
	
	public AnsRPList() {
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

	public AnsRPList(String cr, String descripcion, List<RolePermission> contenido) {
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
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
	public List<RolePermission> getContenido() {
		return contenido;
	}
	public void setContenido(List<RolePermission> contenido) {
		this.contenido = contenido;
	}
	
}
