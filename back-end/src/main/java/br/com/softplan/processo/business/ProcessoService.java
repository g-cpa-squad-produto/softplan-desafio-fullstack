package br.com.softplan.processo.business;

import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ProcessoExistenteException;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.security.business.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    public List<Processo> buscarTodos(){

        if(usuarioLogadoService.usuarioPossuiPermissao("Processo.DelegarParecer")){
            return processoRepository.findAll();
        }

        return processoRepository.buscarProcessosComParecer(usuarioLogadoService.getUsuarioLogado().getId());
    }

    public Processo criar(Processo processo) {
        validarProcessoDuplicado(processo);
        return processoRepository.save(processo);
    }

    private void validarProcessoDuplicado(Processo processo) {
        Processo processoExistente = processoRepository.findByNumero(processo.getNumero());
        if(Objects.nonNull(processoExistente)){
            throw new ProcessoExistenteException();
        }
    }
}
