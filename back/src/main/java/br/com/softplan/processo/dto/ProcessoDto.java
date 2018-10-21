package br.com.softplan.processo.dto;

import java.sql.Timestamp;

import br.com.softplan.processo.modelos.Processo;

public class ProcessoDto {

	public Integer id;

	public String nome;

	public Timestamp dataHora;

	public Boolean ativo;

	public ProcessoDto(Processo processo) {
		this.id = processo.getId();
		this.nome = processo.getNome();
		this.dataHora = processo.getDataHora();
		this.ativo = processo.getAtivo();
	}
}
