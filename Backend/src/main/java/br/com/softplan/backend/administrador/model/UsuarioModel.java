package br.com.softplan.backend.administrador.model;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class UsuarioModel {
	@Id
	private int idUsuario;
	private String nome;
	private Date dataNascimento;
}
