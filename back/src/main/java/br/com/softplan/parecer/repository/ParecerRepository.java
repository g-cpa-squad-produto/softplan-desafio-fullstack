package br.com.softplan.parecer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softplan.parecer.modelos.Parecer;

/**
 * @author emanuel
 *
 */
@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Integer> {
	Parecer save(Parecer persiste);

	@Query("SELECT parecer FROM Parecer parecer WHERE parecer.processo.id = :idProcesso AND  parecer.deletado = false ")
	List<Parecer> listarParecerPorProcesso(@Param("idProcesso") Integer idProcesso);
}
