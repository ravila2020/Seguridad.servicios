package com.mtto.sat.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Logon {
	
	  private String tranid;
	  private String status;
	  private String codigo;
	  private String mensaje;
	  private String numtask;

	  public Logon() {
	  }	  
	  
	public String getTranid() {
		return tranid;
	}
	public void setTranid(String tranid) {
		this.tranid = tranid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getNumtask() {
		return numtask;
	}
	public void setNumtask(String numtask) {
		this.numtask = numtask;
	}

	@Override
	  public String toString() {
	    return " + Logon {" + " tranid=" + tranid + ", status='" + status + ", codigo='" + codigo 
	    	              	+ ", mensaje='" + mensaje + ", numtask='" + numtask + '\'' + '}';
	  }	
	
}
