package br.com.softplan.backend.administrador.request;

import java.sql.Date;

import lombok.Data;

import br.com.softplan.backend.administrador.model.UsuarioModel;

@Data
public class UsuarioRequest {
	private String nome;
	private Date dataNascimento;
	public UsuarioModel toUsuarioModel() {
		return UsuarioModel.builder().nome(this.nome).dataNascimento(this.dataNascimento).build();
	}
}
