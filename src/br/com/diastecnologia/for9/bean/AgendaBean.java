package br.com.diastecnologia.for9.bean;
import java.io.Serializable;
import java.util.List;

public class AgendaBean implements Serializable {

	private static final long serialVersionUID = -4789203658771001800L;
	
	private Integer agendaId;
	private String nome;
	private String dateInicioEvento;
	private String dateFimEvento;
	private String local;
	private String site;
	private String descricao;
	private ImagemBean imagemPrincipal;
	private List<ImagemBean> listImagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDateInicioEvento() {
		return dateInicioEvento;
	}

	public void setDateInicioEvento(String dateInicioEvento) {
		this.dateInicioEvento = dateInicioEvento;
	}

	public String getDateFimEvento() {
		return dateFimEvento;
	}

	public void setDateFimEvento(String dateFimEvento) {
		this.dateFimEvento = dateFimEvento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ImagemBean getImagemPrincipal() {
		return imagemPrincipal;
	}

	public void setImagemPrincipal(ImagemBean imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}

	public List<ImagemBean> getListImagem() {
		return listImagem;
	}

	public void setListImagem(List<ImagemBean> listImagem) {
		this.listImagem = listImagem;
	}

	public Integer getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Integer agendaId) {
		this.agendaId = agendaId;
	}
}
