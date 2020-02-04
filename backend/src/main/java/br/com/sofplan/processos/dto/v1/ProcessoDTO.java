package br.com.sofplan.processos.dto.v1;

import java.time.OffsetDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import br.com.sofplan.processos.enums.Situacao;

public class ProcessoDTO {

	@Null
	private Long id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;

	@Null
	private ParecerDTO parecer;

	@Null
	private UsuarioDTO criadoPor;

	private Set<UsuarioDTO> responsaveis;

	@Null
	private OffsetDateTime dataCriacao;
	
	@Null
	private Situacao situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ParecerDTO getParecer() {
		return parecer;
	}

	public void setParecer(ParecerDTO parecer) {
		this.parecer = parecer;
	}

	public UsuarioDTO getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(UsuarioDTO criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Set<UsuarioDTO> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<UsuarioDTO> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Situacao getSituacao() {
		return situacao == null ? Situacao.PENDENTE : situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
}
