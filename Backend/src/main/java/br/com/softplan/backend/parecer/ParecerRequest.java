package br.com.softplan.backend.parecer;

import lombok.Data;

@Data
public class ParecerRequest {

	private String usuarioId;
	private String processoId;
	public ParecerModel toParecerModel() {
		return ParecerModel.builder().processoId(this.processoId).usuarioId(this.usuarioId).build();
	}
}
