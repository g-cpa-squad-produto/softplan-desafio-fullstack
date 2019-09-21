package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.implementation.repository.ProcessoRepository;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerificarUsuarioRelacionadoComProcessos {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ParecerRepository parecerRepository;

    public Boolean executar(Long usuarioId) {
        List<Processo> processosCriadosPeloUsuario = processoRepository.findAllByUsuarioCriacaoId(usuarioId);
        List<Parecer> pareceresDoUsuario = parecerRepository.findAllByUsuarioId(usuarioId);
        return processosCriadosPeloUsuario.size() > 0 | pareceresDoUsuario.size() > 0;
    }
}
