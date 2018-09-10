package br.com.sitalobr.dev.desafio.repository;

import br.com.sitalobr.dev.desafio.entity.Role;
import br.com.sitalobr.dev.desafio.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Interface responsável por especificar operações padrão relacionadas a entidade {@link Usuario} com o banco de dados
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Boolean existsByUsername(String username);
    Optional<Usuario> findByUsername(String username);
    Iterable<Usuario> findAllByRoles(Set<Role> roles);
}
