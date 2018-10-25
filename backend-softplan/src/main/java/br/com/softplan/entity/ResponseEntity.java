package br.com.softplan.entity;

public class ResponseEntity {
	
	/**
	 * 1 - Sucesso
	 * 2 - Alerta
	 * 3 - Info
	 * 4 - Erro
	 */
	private Integer id;
	
	private String mensagem;
	
	public ResponseEntity(Integer id, String mensagem) {
		this.id = id;
		this.mensagem = mensagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
