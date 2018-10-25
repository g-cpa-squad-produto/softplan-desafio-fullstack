package br.com.softplan.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@SuppressWarnings("unchecked")
	Usuario save(Usuario persiste);

	@Query("SELECT u FROM Usuario u WHERE login = :login and u.ativo = true")
	public Usuario buscarUsuarioPeloLogin(@Param("login") String login);

	@Query("SELECT u FROM Usuario u WHERE  u.deletado = false")
	public List<Usuario> listarUsuarios();
}
