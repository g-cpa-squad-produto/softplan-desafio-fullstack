package br.com.softplan.processo.business;

import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.processo.entity.ParecerProcesso;
import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ParecerProcessoNaoExistenteException;
import br.com.softplan.processo.exception.ProcessoNaoExistenteException;
import br.com.softplan.processo.repository.ParecerProcessoRepository;
import br.com.softplan.processo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParecerProcessoService {

    @Autowired
    private ParecerProcessoRepository repository;

    @Autowired
    private ProcessoRepository processoRepository;

    public List<ParecerProcessoDTO> buscarTodos(Long processoId){
        return repository.buscar(processoId);
    }

    public ParecerProcesso criar(Long processoId, ParecerProcesso parecerProcesso) {

        Processo processo = processoRepository.findById(processoId).orElseThrow(() -> new ProcessoNaoExistenteException());
        parecerProcesso.setProcesso(processo);

        return repository.save(parecerProcesso);
    }

    public ParecerProcesso editar(Long id, ParecerProcesso parecerProcesso) {
        ParecerProcesso parecerExistente = repository.findById(id).orElseThrow(() -> new ParecerProcessoNaoExistenteException());
        parecerExistente.setParecer(parecerProcesso.getParecer());
        return repository.save(parecerProcesso);
    }

}
