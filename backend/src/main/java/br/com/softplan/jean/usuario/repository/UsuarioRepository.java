package br.com.softplan.jean.usuario.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.jean.usuario.entity.Usuario;
import br.com.softplan.jean.util.TipoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Set<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
	Usuario findByLogin(String login);
	Usuario findByToken(String token);
	
}
