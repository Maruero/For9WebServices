package br.com.diastecnologia.for9.bean;
import java.io.Serializable;
import java.util.List;

/**
 * @author diecoz
 * 
 */
public class CategoriaAssociadoBean implements Serializable {

	private static final long serialVersionUID = 5398206633900156318L;
	
	private int id;
	private String nome;
	private ImagemBean imagem;
	private List<AssociadoBean> listAssociado;

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


	public List<AssociadoBean> getListAssociado() {
		return listAssociado;
	}

	public void setListAssociado(List<AssociadoBean> listAssociado) {
		this.listAssociado = listAssociado;
	}

	public ImagemBean getImagem() {
		return imagem;
	}

	public void setImagem(ImagemBean imagem) {
		this.imagem = imagem;
	}

}
