package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ProcessoRepository;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarProcessoPorId {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo executar(Long processoId) {
        return processoRepository.findById(processoId).orElse(null);
    }
}
