package br.com.softplan.desafio.repository;

import br.com.softplan.desafio.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    @Query(" SELECT p FROM Processo p " +
           "  WHERE p.dataFinalizacao IS NULL ")
    List<Processo> findAllPendentes();

    @Query(" SELECT p FROM Processo p " +
           "  WHERE p.codigo = :codigo ")
    Optional<Processo> getByCodigo(@Param("codigo") Long codigo);
}
