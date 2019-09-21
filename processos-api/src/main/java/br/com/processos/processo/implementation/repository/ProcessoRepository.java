package br.com.processos.processo.implementation.repository;

import br.com.processos.processo.specification.entity.Processo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends CrudRepository<Processo, Long> {

    @Query("SELECT p from Processo p JOIN FETCH p.usuarioCriacao WHERE p.id = :processoId")
    Optional<Processo> findById(@Param("processoId") Long processoId);

    @Query("SELECT p FROM Processo p JOIN FETCH p.usuarioCriacao")
    List<Processo> findAllAndFetchDependencies();
}
