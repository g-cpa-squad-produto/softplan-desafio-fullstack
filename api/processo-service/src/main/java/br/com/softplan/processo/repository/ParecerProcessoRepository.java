package br.com.softplan.processo.repository;

import br.com.softplan.processo.entidades.ParecerProcesso;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParecerProcessoRepository  extends PagingAndSortingRepository<ParecerProcesso, Long> {
}
