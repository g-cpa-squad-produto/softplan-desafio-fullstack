package br.com.softplan.jean.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.jean.usuario.entity.Usuario;
import br.com.softplan.jean.usuario.entity.UsuarioDTO;
import br.com.softplan.jean.usuario.repository.UsuarioRepository;
import br.com.softplan.jean.util.TipoUsuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listar() {
		return UsuarioDTO.toListDTO(usuarioRepository.findAll());
	}
	
	public UsuarioDTO trazer(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
		return UsuarioDTO.toDTO(usuario);
	}

	public Usuario criar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario alterar(Long usuarioId, Usuario usuarioRequest) {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			usuario.setNome(usuarioRequest.getNome());
			usuario.setLogin(usuarioRequest.getLogin());
			usuario.setSenha(usuarioRequest.getSenha());
			return usuarioRepository.save(usuario);
		}).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}

	public void deletar(Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			throw new RuntimeException("Usuario não encontrado!");
		}
		usuarioRepository.findById(usuarioId).map(usuario -> {
			usuarioRepository.delete(usuario);
			return true;
		}).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}
	
	public List<UsuarioDTO> listarFinalizadores() {
		return UsuarioDTO.toListDTO(usuarioRepository.findByTipoUsuario(TipoUsuario.FINALIZADOR));
	}
	
	public UsuarioDTO trazerPorToken(String token) {
		String tokenF = token.substring(7, token.length());
		UsuarioDTO dto = UsuarioDTO.toDTO(usuarioRepository.findByToken(tokenF));
		return dto;
	}
	
	public Usuario trazerPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
	public Usuario alterarToken(Long usuarioId, Usuario usuarioRequest) {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			usuario.setToken(usuarioRequest.getToken());
			return usuarioRepository.save(usuario);
		}).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}

}
