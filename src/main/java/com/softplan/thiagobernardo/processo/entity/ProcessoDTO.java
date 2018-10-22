package com.softplan.thiagobernardo.processo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.softplan.thiagobernardo.usuario.entity.UsuarioDTO;
import com.softplan.thiagobernardo.util.ParecerStatus;

public class ProcessoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5361818186621806240L;

	private Long id;
	private String numero;
    private String descricao;
    private ParecerStatus statusParecer;
	private List<UsuarioDTO> usuariosPararecer;
	
	public ProcessoDTO() {
		super();
	}

	public ProcessoDTO(Long id, String numero, String descricao, ParecerStatus statusParecer,
			List<UsuarioDTO> usuariosPararecer) {
		super();
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.statusParecer = statusParecer;
		this.usuariosPararecer = usuariosPararecer;
	}

	public static ProcessoDTO toDTO(Processo processo) {
		if(processo!= null) {
			return new ProcessoDTO(processo.getId(),
					processo.getNumero(),
					processo.getDescricao(),
					processo.getStatusParecer(),
					UsuarioDTO.toListDTO(processo.getUsuariosPararecer()));
		}else {
			return null;
		}
	}
	
	public static List<ProcessoDTO> toListDTO(List<Processo> lista){
		List<ProcessoDTO> listaDTO = null;
		if(lista != null) {
			 listaDTO = lista.stream().map(processo -> (toDTO(processo))).collect(Collectors.toList());
		}
		return listaDTO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<UsuarioDTO> getUsuariosPararecer() {
		return usuariosPararecer;
	}
	public void setUsuariosPararecer(List<UsuarioDTO> usuariosPararecer) {
		this.usuariosPararecer = usuariosPararecer;
	}
	public ParecerStatus getStatusParecer() {
		return statusParecer;
	}
	public void setStatusParecer(ParecerStatus statusParecer) {
		this.statusParecer = statusParecer;
	}

}
