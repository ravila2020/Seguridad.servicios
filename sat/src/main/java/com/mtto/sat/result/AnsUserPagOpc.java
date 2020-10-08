package com.mtto.sat.result;

import java.util.List;
import java.util.Optional;

import com.mtto.sat.modelo.AppUser;

public class AnsUserPagOpc {

	private String cr;
	private String descripcion;
	private Optional<AppUser> contenido;
	
	
	public AnsUserPagOpc() {
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
	public Optional<AppUser> getContenido() {
		return contenido;
	}
	public void setContenido(Optional<AppUser> contenido) {
		this.contenido = contenido;
	}
	

	
}
