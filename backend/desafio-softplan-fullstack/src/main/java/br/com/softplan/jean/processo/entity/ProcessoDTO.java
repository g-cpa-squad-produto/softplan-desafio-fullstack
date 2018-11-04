package br.com.softplan.jean.processo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.softplan.jean.usuario.entity.UsuarioDTO;
import br.com.softplan.jean.util.ParecerStatus;

public class ProcessoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5361818186621806240L;

	private Long id;
	private String numero;
    private String descricao;
    private ParecerStatus statusParecer;
	private List<UsuarioDTO> usuariosParecer;
	
	public ProcessoDTO() {
		super();
	}

	public ProcessoDTO(Long id, String numero, String descricao, ParecerStatus statusParecer,
			List<UsuarioDTO> usuariosParecer) {
		super();
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.statusParecer = statusParecer;
		this.usuariosParecer = usuariosParecer;
	}

	public static ProcessoDTO toDTO(Processo processo) {
		if(processo!= null) {
			return new ProcessoDTO(processo.getId(),
					processo.getNumero(),
					processo.getDescricao(),
					processo.getStatusParecer(),
					UsuarioDTO.toListDTO(processo.getUsuariosParecer()));
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
	public List<UsuarioDTO> getUsuariosParecer() {
		return usuariosParecer;
	}
	public void setUsuariosParecer(List<UsuarioDTO> usuariosParecer) {
		this.usuariosParecer = usuariosParecer;
	}
	public ParecerStatus getStatusParecer() {
		return statusParecer;
	}
	public void setStatusParecer(ParecerStatus statusParecer) {
		this.statusParecer = statusParecer;
	}

}
