package br.com.softplan.processo.services;

import br.com.softplan.processo.entidades.ParecerProcesso;
import br.com.softplan.processo.entidades.Processo;
import br.com.softplan.processo.repository.ParecerProcessoRepository;
import br.com.softplan.processo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository;

    @Autowired
    private ParecerProcessoRepository parecerRepository;

    public Processo buscarProcesso(Long codigo) {
        Optional<Processo> processoOptional = repository.findById(codigo);
        return processoOptional.get();
    }

    public void salvarProcesso(Processo processo) {
        repository.save(processo);
    }

    public void excluirProcesso(Processo processo) {
        repository.delete(processo);
    }

}
