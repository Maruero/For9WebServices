package br.com.diastecnologia.for9.bean;

import java.io.Serializable;

public class PaisBean implements Serializable{

	private static final long serialVersionUID = -6357304983984798101L;
	
	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
