package br.com.softplan.core.service;

import br.com.softplan.core.model.AbstractEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


/**
 * Interface com os métodos padrão para os serviços de CRUD do projeto
 *
 * @param <ENTIDADE> Entidade que o serviço gerencia
 * @param <ID>       Classe que representa a chave primaria
 * @author Samuel Correia Guimarães
 */
public interface CrudService<ENTIDADE extends AbstractEntity, ID extends Serializable> {

    /**
     * Realiza a consulta da entidade com o ID informado
     *
     * @param id ID da entidade
     * @return Registro encontrado
     */
    Optional<ENTIDADE> pesquisarPorId(ID id);

    //TODO Vai ficar ou vai filtrar???
    List<ENTIDADE> pesquisarTodos();

    Page<ENTIDADE> filtrar(JsonNode jsonFiltro, Pageable paginacao);

    /**
     * Realiza o cadastro do documento informado no banco de dados
     *
     * @param documento documento a ser cadastrado
     * @return Documento recém cadastrado com o ID gerado
     */
    ENTIDADE cadastrar(ENTIDADE documento);

    /**
     * Realiza a alteração do documento informado no banco de dados
     *
     * @param documento documento com os novos valores a ser alterado
     * @return Documento recém alterada
     */
    ENTIDADE alterar(ENTIDADE documento);

    /**
     * Realiza a exclusão do documento informado no banco de dados
     *
     * @param id ID do documento a ser excluído
     */
    void excluir(ID id);

}
