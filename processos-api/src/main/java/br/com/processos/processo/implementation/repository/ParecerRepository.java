package br.com.processos.processo.implementation.repository;

import br.com.processos.processo.specification.entity.EnumParecerSituacao;
import br.com.processos.processo.specification.entity.Parecer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParecerRepository extends CrudRepository<Parecer, Long> {

    @Query("SELECT p FROM Parecer p JOIN FETCH p.usuario JOIN FETCH p.processo pr JOIN FETCH pr.usuarioCriacao WHERE p.id = :parecerId")
    Optional<Parecer> findById(@Param("parecerId") Long parecerId);

    @Query("SELECT p FROM Parecer p JOIN FETCH p.usuario JOIN FETCH p.processo pr JOIN FETCH pr.usuarioCriacao WHERE p.usuario.id = :usuarioId AND p.situacao = :situacao")
    List<Parecer> findAllByUsuarioIdAndSituacao(@Param("usuarioId") Long usuarioId, @Param("situacao") EnumParecerSituacao situacao);

}
