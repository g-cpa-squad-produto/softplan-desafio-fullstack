package br.com.softplan.backend.usuario;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
@Data
@Builder
public class UsuarioModel {

	@Id
	private String usuarioId;
	private String nome;
	private String email;
	private String dataNascimento;
}
