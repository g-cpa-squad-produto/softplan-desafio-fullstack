package br.com.sitalobr.dev.desafio.service;

import br.com.sitalobr.dev.desafio.entity.Processo;
import br.com.sitalobr.dev.desafio.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por tratar as regras de negócio relacionadas a entidade {@link Processo}
 */
@Service
public class ProcessoService extends AbstractService<Processo, Long> {
    private final ProcessoRepository processoRepository;

    @Autowired
    public ProcessoService(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }
    
    @Override
    public ProcessoRepository getRepository() {
        return this.processoRepository;
    }

    /**
     * Função responsável por recuperar todos os Processos cadastrados
     * @return Lista de Processos registrados
     */
    public Iterable<Processo> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * Função responsável por registrar um {@link Processo}; caso o ID especificado seja null, um novo registro é cadastrado;
     * caso o ID esteja presente, o registro existente é atualizado
     * @param entity Objeto {@link Processo} contendo os dados a serem registrados
     * @return Objeto {@link Processo} recém-registrado
     */
    public Processo save(Processo entity) {
        return this.getRepository().save(entity);
    }

    /**
     * Função responsável por atualizar um registro de {@link Processo}
     * @param id Long contendo o ID da {@link Processo} a ser atualizado
     * @param request Objeto contendo os dados de {@link Processo}
     * @return Objeto {@link Processo} recém-atualizado
     */
    public Processo update(Long id, Processo request) {
        Processo processo = this.findById(id);
        processo.setDescricao(request.getDescricao());

        return this.save(processo);
    }

    /**
     * Função responsável por excluir {@link Processo}
     * @param id Long contendo o ID do {@link Processo} a ser excluído
     */
    public void delete(Long id) {
        this.getRepository().deleteById(id);
    }
}
