package br.com.processo.prjdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.processo.prjdemo.model.EnumPermissaoUsuario;
import br.com.processo.prjdemo.model.Usuario;

/**
 * 
 * @author Guilherme Sena
 * Repositorio JPA para as consultas relacionadas ao usuario
 */
@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("select u from Usuario u where u.login = :login and u.senha = :senha")
	Usuario getUsuarioLoginSenha(@Param("login") String login, @Param("senha") String senha);
	
	@Query("select u from Usuario u Join u.lstPermissao p WHERE p in (:permissao)")
	List<Usuario> getLstUsuarioPorPermissao(@Param("permissao") EnumPermissaoUsuario permissao);
}
