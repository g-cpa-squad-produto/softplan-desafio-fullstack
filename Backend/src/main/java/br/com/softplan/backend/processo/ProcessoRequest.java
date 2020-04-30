package br.com.softplan.backend.processo;

import lombok.Data;

@Data
public class ProcessoRequest {

	private String titulo;
	private String descricao;
	public ProcessoModel toProcessoModel() {
		return ProcessoModel.builder().titulo(this.titulo).descricao(this.descricao).build();
	}
}
