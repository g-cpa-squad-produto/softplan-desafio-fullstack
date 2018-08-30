package br.com.softplan.desafio.repository;

import br.com.softplan.desafio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(" SELECT u FROM Usuario u " +
           "  WHERE u.codigo = :codigo AND u.perfil = 'FIN' ")
    Optional<Usuario> findByCodigoAndPerfilFinalizador(@Param("codigo") Long codigo);

    @Query(" SELECT u FROM Usuario u " +
            "  WHERE u.codigo = :codigo AND u.perfil = 'TRI' ")
    Optional<Usuario> findByCodigoAndPerfilTriador(@Param("codigo") Long codigo);

    @Query(" SELECT u FROM Usuario u WHERE u.nome = :nome ")
    Optional<Usuario> findByNome(@Param("nome") String nome);
}
