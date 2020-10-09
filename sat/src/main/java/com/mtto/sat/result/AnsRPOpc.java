package com.mtto.sat.result;

import java.util.Optional;

import com.mtto.sat.modelo.RolePermission;

public class AnsRPOpc {

	private String cr;
	private String descripcion;
	private Optional<RolePermission> contenido;
		
	public AnsRPOpc() {
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	
	public AnsRPOpc(String cr, String descripcion, Optional<RolePermission> contenido) {
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
	public Optional<RolePermission> getContenido() {
		return contenido;
	}
	public void setContenido(Optional<RolePermission> contenido) {
		this.contenido = contenido;
	}
	
	
}
