package br.com.processo.prjdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.processo.prjdemo.model.Processo;

/**
 * 
 * @author Guilherme Sena
 * Repositorio JPA para o objeto Processo
 */
@Repository("processoRepository")
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
	@Query("select p from Processo p Join p.lstUsuParecer u LEFT JOIN p.lstUsuParecer pa WHERE u.id = :idUsu and pa.id is null")
	List<Processo> getLstUsuarioPorPermissao(@Param("idUsu") Long idUsu);
}
