package com.softplan.processos.features.processosatribuidos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProcessoAtribuidoRepository extends PagingAndSortingRepository<ProcessoAtribuido, Long> {

    Page<ProcessoAtribuido> findByIdUsuarioFinalizadorAndPossuiParecer(Long idUsuarioFinalizador,
                                                                       SimNao possuiParecer,
                                                                       Pageable pageable);

}
