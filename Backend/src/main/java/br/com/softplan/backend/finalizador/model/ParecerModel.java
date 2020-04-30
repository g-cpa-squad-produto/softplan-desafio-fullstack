package br.com.softplan.backend.finalizador.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parecer")
@Data
@Builder
public class ParecerModel {

	@Id
	private String parecerId;
	private String resposta;
	private String usuarioId;
	private String processoId;

}
