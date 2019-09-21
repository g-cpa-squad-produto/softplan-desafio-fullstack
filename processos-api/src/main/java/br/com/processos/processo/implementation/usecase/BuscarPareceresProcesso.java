package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.specification.entity.Parecer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarPareceresProcesso {

    @Autowired
    private ParecerRepository parecerRepository;

    public List<Parecer> executar(Long processoId) {
        return parecerRepository.findAllByProcessoId(processoId);
    }
}
