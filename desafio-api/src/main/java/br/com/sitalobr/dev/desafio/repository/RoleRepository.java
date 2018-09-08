package br.com.sitalobr.dev.desafio.repository;

import br.com.sitalobr.dev.desafio.entity.Role;
import br.com.sitalobr.dev.desafio.entity.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por especificar operações padrão relacionadas a entidade {@link Role} com o banco de dados
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByNome(RoleName nome);
}
