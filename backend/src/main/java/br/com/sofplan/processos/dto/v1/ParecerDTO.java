package br.com.sofplan.processos.dto.v1;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import br.com.sofplan.processos.enums.Situacao;
import io.swagger.annotations.ApiModelProperty;

public class ParecerDTO {

	@Null
	private Long id;

	@NotBlank
	private String texto;

	@NotNull
	private Situacao situacao;

	@Null
	@ApiModelProperty(notes = "campo somente leitura", required = false)
	private UsuarioDTO criadoPor;

	@Null
	private OffsetDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public UsuarioDTO getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(UsuarioDTO criadoPor) {
		this.criadoPor = criadoPor;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
