package br.com.softplan.backend.triador.request;

import lombok.Data;

import br.com.softplan.backend.triador.model.ProcessoModel;

@Data
public class ProcessoRequest {

	private String titulo;
	private String descricao;
	public ProcessoModel toProcessoModel() {
		return ProcessoModel.builder().titulo(this.titulo).descricao(this.descricao).build();
	}
}
