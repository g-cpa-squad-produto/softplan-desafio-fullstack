package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.specification.entity.EnumParecerSituacao;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuscarProcessosPendentesParecer {

    @Autowired
    private ParecerRepository parecerRepository;

    public List<Processo> executar(Long usuarioId) {
        List<Parecer> pareceres = parecerRepository.findAllByUsuarioIdAndSituacao(usuarioId, EnumParecerSituacao.PENDENTE);
        List<Processo> processos = new ArrayList<>();
        pareceres.forEach(parecer -> processos.add(parecer.getProcesso()));
        return processos;
    }
}
