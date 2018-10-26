package br.com.softplan.models;

public enum StatusProcess {

	CRIADO("CRIADO"), PENDENTE("PENDENTE"), FINALIADO("FINALIADO");
	
	public String status;
	
	StatusProcess(String status) {
		this.status = status;
	}
}
