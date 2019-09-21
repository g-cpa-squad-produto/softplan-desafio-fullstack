package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ProcessoRepository;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarTodosProcessos {

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> executar() {
        return (List<Processo>) processoRepository.findAllAndFetchDependencies();
    }
}
