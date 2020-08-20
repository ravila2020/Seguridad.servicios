package com.mtto.sat.result;

import java.time.ZonedDateTime;

public class GenericResponse {

	private Integer codigo;
	private String mensaje;
	private ZonedDateTime fechaHora;
	

	public GenericResponse() {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
	}

	public GenericResponse(Integer codigo, String mensaje, ZonedDateTime fechaHora) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setFechaHora(ZonedDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMensaje() {
		return mensaje;
	}
	public ZonedDateTime getFechaHora() {
		return fechaHora;
	}
	
}
