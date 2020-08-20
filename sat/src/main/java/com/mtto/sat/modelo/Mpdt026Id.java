package com.mtto.sat.modelo;

import java.io.Serializable;

public class Mpdt026Id implements Serializable {

	private String  codent;
	private Integer codmar;
	private Integer indtipt;

	public Mpdt026Id() {
		this.codent  = codent;
		this.codmar  = codmar;
		this.indtipt = indtipt;		
	}
	
	public Mpdt026Id (String codent, Integer codmar, Integer indtipt) {
		this.codent = codent;
		this.codmar = codmar;
		this.indtipt = indtipt;
	}

	public String getCodent() {
		return codent;
	}

	public void setCodent(String codent) {
		this.codent = codent;
	}

	public Integer getCodmar() {
		return codmar;
	}

	public void setCodmar(Integer codmar) {
		this.codmar = codmar;
	}

	public Integer getIndtipt() {
		return indtipt;
	}

	public void setIndtipt(Integer indtipt) {
		this.indtipt = indtipt;
	}
}
