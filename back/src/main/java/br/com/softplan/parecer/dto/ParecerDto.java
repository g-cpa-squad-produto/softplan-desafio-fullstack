package br.com.softplan.parecer.dto;

import java.sql.Timestamp;

import br.com.softplan.parecer.modelos.Parecer;
import br.com.softplan.processo.dto.ProcessoDto;

public class ParecerDto {

	public Integer id;

	public String nome;

	public Timestamp dataHora;

	public ProcessoDto processo;

	public ParecerDto(Parecer parecer) {
		this.id = parecer.getId();
		this.nome = parecer.getNome();
		this.dataHora = parecer.getDataHora();
		this.processo = new ProcessoDto(parecer.getProcesso());
	}
}
