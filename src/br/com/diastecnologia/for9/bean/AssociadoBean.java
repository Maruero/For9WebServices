package br.com.diastecnologia.for9.bean;
import java.io.Serializable;
import java.util.List;

public class AssociadoBean implements Serializable {

	private static final long serialVersionUID = 2055301846470847144L;
	
	private int id;
	private String nome;
	private String telefone;
	private String email;
	private PaisBean pais;
	private String site;
	private List<ImagemBean> listImagem;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PaisBean getPais() {
		return pais;
	}

	public void setPais(PaisBean pais) {
		this.pais = pais;
	}

	public List<ImagemBean> getListImagem() {
		return listImagem;
	}

	public void setListImagem(List<ImagemBean> listImagem) {
		this.listImagem = listImagem;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
