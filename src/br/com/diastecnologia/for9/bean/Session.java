package br.com.diastecnologia.for9.bean;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Session implements Serializable{

	private static final long serialVersionUID = 5155264223799859656L;
	private LoginBean userLogged;
	
	public boolean isLogged(){
		return userLogged != null;
	}
	
	public LoginBean getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(LoginBean userLogged) {
		this.userLogged = userLogged;
	}
	
}
