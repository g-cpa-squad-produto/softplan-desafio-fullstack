package br.com.softplan.backend.finalizador.request;

import java.util.Optional;

import lombok.Data;

import br.com.softplan.backend.finalizador.model.ParecerModel;
import br.com.softplan.backend.triador.model.ProcessoModel;
import br.com.softplan.backend.triador.service.ProcessoService;

@Data
public class ParecerRequest {
	private final ProcessoService processoService;

	private String usuarioId;
	private String processoId;
	public ParecerModel toParecerModel() {
		return ParecerModel.builder().processoId(processoService.findById(this.processoId)).usuarioId(this.usuarioId).build();
	}
}
