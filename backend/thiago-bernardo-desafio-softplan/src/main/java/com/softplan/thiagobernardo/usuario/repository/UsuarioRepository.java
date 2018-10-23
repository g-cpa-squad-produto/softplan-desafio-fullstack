package com.softplan.thiagobernardo.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.util.TipoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	/**
	 * Retorna uma lista de usuario com base no tipo de usuario
	 * @param tipoUsuario
	 * @return
	 */
	List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
	
	/**
	 * Retorna um usuario com base no login
	 * @param login
	 * @return
	 */
	Usuario findByLogin(String login);
	
	/**
	 * Retorna um usuario com base no token
	 * @param token
	 * @return
	 */
	Usuario findByToken(String token);
	
}
