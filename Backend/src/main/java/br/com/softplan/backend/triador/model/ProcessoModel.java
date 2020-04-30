package br.com.softplan.backend.triador.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "processo")
@Data
@Builder
public class ProcessoModel {

	@Id
	private String processoId;
	private String titulo;
	private String descricao;
}
