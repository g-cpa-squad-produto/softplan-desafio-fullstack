package br.com.softplan.processo.repository;

import br.com.softplan.processo.entidades.Processo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProcessoRepository extends PagingAndSortingRepository<Processo, Long> {
}
