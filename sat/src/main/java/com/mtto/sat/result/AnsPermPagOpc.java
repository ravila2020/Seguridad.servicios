package com.mtto.sat.result;

import java.util.Optional;

import com.mtto.sat.modelo.Permission;

public class AnsPermPagOpc {

	private String cr;
	private String descripcion;
	private Optional<Permission> contenido;
	
	public AnsPermPagOpc() {
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
	public Optional<Permission> getContenido() {
		return contenido;
	}
	public void setContenido(Optional<Permission> contenido) {
		this.contenido = contenido;
	}
	
}
