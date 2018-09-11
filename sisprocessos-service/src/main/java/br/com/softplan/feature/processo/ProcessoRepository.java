package br.com.softplan.feature.processo;

import br.com.softplan.feature.processo.model.Processo;
import br.com.softplan.feature.processo.model.StatusProcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    Page<Processo> findByStatus(StatusProcesso status, Pageable paginacao);

}
