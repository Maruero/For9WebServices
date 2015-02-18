package br.com.diastecnologia.for9.bean;

import java.io.Serializable;

/**
 * @author diecoz
 *
 */
public class DefaultBean implements Serializable{

	private static final long serialVersionUID = 2120853424162047064L;
	
	private int errorCode;
	private String errorMessage;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
