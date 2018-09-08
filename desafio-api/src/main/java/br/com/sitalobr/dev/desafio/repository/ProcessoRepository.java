package br.com.sitalobr.dev.desafio.repository;

import br.com.sitalobr.dev.desafio.entity.Processo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por especificar operações padrão relacionadas a entidade {@link Processo} com o banco de dados
 */
@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long> {
}
