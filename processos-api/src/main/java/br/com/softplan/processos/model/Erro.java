package br.com.softplan.processos.model;

public class Erro {

    private String mensagem;

    public Erro(String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }
}
