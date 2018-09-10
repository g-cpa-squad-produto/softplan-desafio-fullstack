package br.com.softplan.feature.processo;

import br.com.softplan.core.service.AbstractCrudService;
import br.com.softplan.feature.processo.model.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoService extends AbstractCrudService<Processo, Long, ProcessoRepository> {

    private final ProcessoMapper mapper;

    @Autowired
    public ProcessoService(ProcessoRepository repository, ProcessoMapper mapper) {
        super(Processo.class, repository);
        this.mapper = mapper;
    }

    @Override
    public void excluir(Long id) {
        throw new UnsupportedOperationException("processo.nao.pode.ser.excluido");
    }
}
