package br.com.diastecnologia.for9.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 2755376079858705302L;
	
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
