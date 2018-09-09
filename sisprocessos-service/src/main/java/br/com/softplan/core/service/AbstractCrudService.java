package br.com.softplan.core.service;

import br.com.softplan.core.exception.BeanValidationException;
import br.com.softplan.core.logger.HasLogging;
import br.com.softplan.core.model.AbstractEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Classe abstrata com as operações e comportamentos padrão para os serviços de CRUD do projeto
 *
 * @param <ENTIDADE> Entidade que o serviço gerencia
 * @param <ID>       Classe que representa a chave primaria
 * @param <REPO>     Repositório do serviço
 * @author Samuel Correia Guimarães
 */
public abstract class AbstractCrudService<ENTIDADE extends AbstractEntity, ID extends Serializable, REPO extends JpaRepository<ENTIDADE, ID>> implements CrudService<ENTIDADE, ID>, HasLogging {

    @Autowired
    private Validator validator;

    private Class<ENTIDADE> entityClazz;
    protected REPO repository;

    public AbstractCrudService(Class<ENTIDADE> entityClass, REPO repository) {
        this.entityClazz = entityClass;
        this.repository = repository;
    }

    // TODO implementar
    public Page<ENTIDADE> filtrar(JsonNode jsonFiltro, Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Optional<ENTIDADE> pesquisarPorId(ID id) {
        return repository.findById(id);
    }

    public List<ENTIDADE> pesquisarTodos() {
        return repository.findAll();
    }

    /**
     * Realiza o cadastro do objeto informado no banco de dados
     *
     * @param objeto Objeto a ser cadastrado
     * @return Entidade recém cadastrada com o ID gerado
     */
    public ENTIDADE cadastrar(ENTIDADE objeto) {
        validarDocumento(objeto);
        return repository.save(objeto);
    }

    /**
     * Realiza a alteração da entidade informada no banco de dados
     *
     * @param entidade Entidade com os novos valores
     * @return Documento recém alterada
     */
    public ENTIDADE alterar(ENTIDADE entidade) {
        validarId(entidade);
        validarDocumento(entidade);
        return repository.save(entidade);
    }

    /**
     * Realiza a exclusão do documento informado no banco de dados
     *
     * @param id ID do documento a ser excluído
     */
    public void excluir(ID id) {
        repository.deleteById(id);
    }

    /**
     * Valida se o documento informado possui chave primaria definida
     *
     * @param documento Entidade a ser validada
     */
    private void validarId(ENTIDADE documento) {
        if (documento.getId() == null) {
            throw new BeanValidationException("id", "Campo ID é obrigatório");
        }
    }

    /**
     * Verifica se o documento informado é valido
     *
     * @param documento documento a ser validado
     */
    private void validarDocumento(ENTIDADE documento) {
        DataBinder binder = new DataBinder(documento, entityClazz.getSimpleName());
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();

        if (result.hasErrors()) {
            throw new BeanValidationException(result.getFieldErrors());
        }
    }

}
