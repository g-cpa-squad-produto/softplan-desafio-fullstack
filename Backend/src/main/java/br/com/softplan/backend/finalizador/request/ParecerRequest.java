package br.com.softplan.backend.finalizador.request;

import lombok.Data;

import br.com.softplan.backend.finalizador.model.ParecerModel;

@Data
public class ParecerRequest {

	private String usuarioId;
	private String processoId;
	public ParecerModel toParecerModel() {
		return ParecerModel.builder().processoId(this.processoId).usuarioId(this.usuarioId).build();
	}
}
