package br.com.softplan.processo.repository;

import br.com.softplan.processo.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    public Processo findByNumero(String numero);

    @Query("SELECT pr FROM Processo pr WHERE pr.id IN ( SELECT pa.processo.id FROM ParecerProcesso pa WHERE pa.usuario.id = :usuarioId)")
    List<Processo> buscarProcessosComParecer(@Param("usuarioId") Long usuarioId);
}
