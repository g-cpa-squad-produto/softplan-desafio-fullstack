package br.com.softplan.models;

public enum StatusProcess {

	CRIADO("CRIADO"), PENDENTE("PENDENTE"), FINALIZADO("FINALIZADO");
	
	public String status;
	
	StatusProcess(String status) {
		this.status = status;
	}
}
