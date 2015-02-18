package br.com.diastecnologia.for9.bean;

import java.io.Serializable;

/**
 * @author diecoz
 * 
 */
public class ImagemBean implements Serializable {

	private static final long serialVersionUID = -3710219797416789010L;
	
	private Integer id;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
