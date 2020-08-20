package com.mtto.sat.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Peticion {
	
	  private Logon login;

	  
	  
	  public Logon getLogin() {
		return login;
	}



	public void setLogin(Logon login) {
		this.login = login;
	}



	@Override
	  public String toString() {
	    return " + Petición {" +
	        "Login='" + login + '}';
	  }	  

}
