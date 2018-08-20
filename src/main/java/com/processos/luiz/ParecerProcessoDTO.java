package com.processos.luiz;

public class ParecerProcessoDTO {
	
	/**
	 * Devido a falta de tempo para fazer o front end estou passando o login do usuário para poder s
	 * aber quais são os processo que foram relacionados com ele
	 */
	private Long codigoProcesso;
	private String login;
	private String Parecer;
	public Long getCodigoProcesso() {
		return codigoProcesso;
	}
	public void setCodigoProcesso(Long codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getParecer() {
		return Parecer;
	}
	public void setParecer(String parecer) {
		Parecer = parecer;
	}
	
	
}
