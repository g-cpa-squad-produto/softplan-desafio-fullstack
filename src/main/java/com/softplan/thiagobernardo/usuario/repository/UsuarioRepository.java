package com.softplan.thiagobernardo.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.util.TipoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
	Usuario findByLogin(String login);
	Usuario findByToken(String token);
	
}
