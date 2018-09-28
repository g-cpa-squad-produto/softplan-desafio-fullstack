package br.com.desafiosoftplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.desafiosoftplan.model.Processo;

/**
 * Repositório de persistencia da entidade {@link Processo}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>
{
}
