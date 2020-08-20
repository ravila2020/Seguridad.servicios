package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Mpdt026Id.class)
@Table(name = "mpdt026")
public class Mpdt026 {


	@Id           
	private String  codent;                           

	@Id           
	private int  codmar;                              
	
	@Id          
	private int  indtipt;                          

	@Column(name = "CLASE", length = 04)	
	private String clase;
	
	@Column(name = "DESTIPT", length = 30)	
	private String destipt;

	@Column(name = "DESTIPTRED", length = 10)	
	private String destiptred;

	@Column(name = "CODENTUMO", length = 4)	
	private String codentumo;

	@Column(name = "CODOFIUMO", length = 4)	
	private String codofiumo;

	@Column(name = "USUARIOUMO", length = 4)	
	private String usuarioumo;

	@Column(name = "CODTERMUMO", length = 4)	
	private String codtermumo;

	@Column(name = "CONTCUR", length = 14 )	
	private String contcur;

	public String getCodent() {
		return codent;
	}

	public void setCodent(String codent) {
		this.codent = codent;
	}

	public int getCodmar() {
		return codmar;
	}

	public void setCodmar(int codmar) {
		this.codmar = codmar;
	}

	public int getIndtipt() {
		return indtipt;
	}

	public void setIndtipt(int indtipt) {
		this.indtipt = indtipt;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getDestipt() {
		return destipt;
	}

	public void setDestipt(String destipt) {
		this.destipt = destipt;
	}

	public String getDestiptred() {
		return destiptred;
	}

	public void setDestiptred(String destiptred) {
		this.destiptred = destiptred;
	}

	public String getCodentumo() {
		return codentumo;
	}

	public void setCodentumo(String codentumo) {
		this.codentumo = codentumo;
	}

	public String getCodofiumo() {
		return codofiumo;
	}

	public void setCodofiumo(String codofiumo) {
		this.codofiumo = codofiumo;
	}

	public String getUsuarioumo() {
		return usuarioumo;
	}

	public void setUsuarioumo(String usuarioumo) {
		this.usuarioumo = usuarioumo;
	}

	public String getCodtermumo() {
		return codtermumo;
	}

	public void setCodtermumo(String codtermumo) {
		this.codtermumo = codtermumo;
	}

	public String getContcur() {
		return contcur;
	}

	public void setContcur(String contcur) {
		this.contcur = contcur;
	}

}
