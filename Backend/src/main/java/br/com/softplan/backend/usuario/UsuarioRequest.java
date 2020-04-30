package br.com.softplan.backend.usuario;

import lombok.Data;

@Data
public class UsuarioRequest {

	private String nome;
	private String email;
	private String dataNascimento;
	public UsuarioModel toUsuarioModel() {
		return UsuarioModel.builder().nome(this.nome).email(this.email).dataNascimento(this.dataNascimento).build();
	}
}
