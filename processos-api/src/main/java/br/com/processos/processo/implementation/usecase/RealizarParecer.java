package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.specification.entity.EnumParecerSituacao;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.exception.ParecerNaoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class RealizarParecer {

    @Autowired
    private ParecerRepository parecerRepository;

    public Parecer executar(Long parecerId, String texto) {
        Parecer parecer = parecerRepository.findById(parecerId).orElse(null);
        validarParecerExistente(parecer);
        atualizarDadosParecer(parecer, texto);
        return parecerRepository.save(parecer);
    }

    private void atualizarDadosParecer(Parecer parecer, String texto) {
        parecer.setTexto(texto);
        parecer.setSituacao(EnumParecerSituacao.REALIZADO);
        parecer.setDataAtualizacao(new Date());
    }

    private void validarParecerExistente(Parecer parecer) {
        if (Objects.isNull(parecer)) {
            throw new ParecerNaoExistenteException();
        }
    }
}
