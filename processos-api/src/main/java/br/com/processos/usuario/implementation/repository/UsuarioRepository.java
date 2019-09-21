package br.com.processos.usuario.implementation.repository;

import br.com.processos.usuario.specification.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.id IN :ids")
    List<Usuario> findAllByIdIn(@Param("ids") List<Long> ids);
}
