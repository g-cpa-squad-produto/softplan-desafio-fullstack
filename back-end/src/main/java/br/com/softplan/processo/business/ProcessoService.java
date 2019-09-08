package br.com.softplan.processo.business;

import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ProcessoExistenteException;
import br.com.softplan.processo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> buscarTodos(){
        return processoRepository.findAll();
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
