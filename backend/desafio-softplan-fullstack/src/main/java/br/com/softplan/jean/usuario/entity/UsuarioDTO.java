package br.com.softplan.jean.usuario.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.softplan.jean.util.TipoUsuario;

public class UsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2170526971507541799L;
	
	private Long id;
    private String nome;
    private String login;
	private TipoUsuario tipoUsuario;
	private String token;
	
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String nome, String login, TipoUsuario tipoUsuario, String token) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.tipoUsuario = tipoUsuario;
		this.token = token;
	}
	
	public static UsuarioDTO toDTO(Usuario usuario) {
		if(usuario!=null) {
			return new UsuarioDTO(usuario.getId(),usuario.getNome(),usuario.getLogin(),usuario.getTipoUsuario(),usuario.getToken());
		}else {
			return null;
		}
	}
	
	public static List<UsuarioDTO> toListDTO(List<Usuario> lista){
		List<UsuarioDTO> listaDTO = null;
		if(lista != null) {
			 listaDTO = lista.stream().map(usuario -> (toDTO(usuario))).collect(Collectors.toList());
		}
		return listaDTO;
	}
	
	public boolean isAdmin() {
		return tipoUsuario.getDescricao().equals(TipoUsuario.ADMIN.getDescricao());
	}
	
	public boolean isTriador() {
		return tipoUsuario.getDescricao().equals(TipoUsuario.TRIADOR.getDescricao());
	}
	
	public boolean isFinalizador() {
		return tipoUsuario.getDescricao().equals(TipoUsuario.FINALIZADOR.getDescricao());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
