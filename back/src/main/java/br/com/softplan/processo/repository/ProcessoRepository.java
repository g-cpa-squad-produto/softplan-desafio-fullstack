package br.com.softplan.processo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softplan.processo.modelos.Processo;

/**
 * @author emanuel
 *
 */
@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
	Processo save(Processo persiste);

	@Query("SELECT p  FROM Processo p   JOIN  p.usuarios u WHERE  u.id =:idUsuario AND  p.finalizado = false AND  p.deletado = false")
	List<Processo> listarProcessosDeUmUsuario(@Param("idUsuario") Integer idUsuario);

	@Query("SELECT p  FROM Processo p  WHERE p.finalizado = false AND  p.deletado = false")
	List<Processo> listarTodosProcessos();

	@Query("SELECT processo FROM Processo processo JOIN  processo.usuarios u WHERE u.id =:idUsuario "
			+ "AND processo.deletado=false  AND processo.finalizado=false "
			+ "AND NOT EXISTS (SELECT 1 FROM Parecer parecer WHERE parecer.processo = processo AND parecer.usuario =u)")
	List<Processo> listarProcessosSemParecer(@Param("idUsuario") Integer idUsuario);
}
