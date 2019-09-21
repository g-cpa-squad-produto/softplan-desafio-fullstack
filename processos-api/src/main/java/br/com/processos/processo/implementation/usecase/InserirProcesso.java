package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ProcessoRepository;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InserirProcesso {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo executar(Processo processo) {
        processo.setDataCriacao(new Date());
        return processoRepository.save(processo);
    }
}
