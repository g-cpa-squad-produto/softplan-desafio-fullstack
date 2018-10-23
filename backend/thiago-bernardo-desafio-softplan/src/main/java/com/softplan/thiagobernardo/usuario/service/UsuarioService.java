package com.softplan.thiagobernardo.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.thiagobernardo.exception.NaoEncontradoException;
import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.usuario.entity.UsuarioDTO;
import com.softplan.thiagobernardo.usuario.repository.UsuarioRepository;
import com.softplan.thiagobernardo.util.TipoUsuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listar() {
		return UsuarioDTO.toListDTO(usuarioRepository.findAll());
	}
	
	public UsuarioDTO trazer(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado!"));
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
		}).orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado!"));
	}

	public void deletar(Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			throw new RuntimeException("Usuario não encontrado!");
		}
		usuarioRepository.findById(usuarioId).map(usuario -> {
			usuarioRepository.delete(usuario);
			return true;
		}).orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado!"));
	}
	
	/**
	 * Lista todos os usuarios do tipo Finalizador
	 * @return
	 */
	public List<UsuarioDTO> listarFinalizadores() {
		return UsuarioDTO.toListDTO(usuarioRepository.findByTipoUsuario(TipoUsuario.FINALIZADOR));
	}
	
	/**
	 * Metodo retorna um usuario com base no token passado como parametro
	 * @param token
	 * @return
	 */
	public UsuarioDTO trazerPorToken(String token) {
		String tokenF = token.substring(7, token.length());
		return UsuarioDTO.toDTO(usuarioRepository.findByToken(tokenF));
	}
	
	/**
	 * Metodo retorna um usuario com base no login passado como parametro
	 * @param login
	 * @return
	 */
	public Usuario trazerPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
	/**
	 * Metodo para atualizar somente o token de um usuario
	 * @param usuarioId
	 * @param usuarioRequest
	 * @return
	 */
	public Usuario alterarToken(Long usuarioId, Usuario usuarioRequest) {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			usuario.setToken(usuarioRequest.getToken());
			return usuarioRepository.save(usuario);
		}).orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado!"));
	}

}
