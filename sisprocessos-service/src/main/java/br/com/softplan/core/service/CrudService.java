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

    /**
     * Realiza a consulta das entidades com os IDs informados
     *
     * @param ids ID das entidades
     * @return Registros encontrados
     */
    List<ENTIDADE> pesquisarPorId(Iterable<ID> ids);

    List<ENTIDADE> pesquisarTodos();

    Page<ENTIDADE> filtrar(JsonNode jsonFiltro, Pageable paginacao);

    /**
     * Realiza o cadastro da entidade informada no banco de dados
     *
     * @param entidade entidade a ser cadastrada
     * @return Entidade recém cadastrada com o ID gerado
     */
    ENTIDADE cadastrar(ENTIDADE entidade);

    /**
     * Realiza a alteração da entidade informada no banco de dados
     *
     * @param entidade entidade com os novos valores a ser alterado
     * @return Entidade recém alterada
     */
    ENTIDADE alterar(ENTIDADE entidade);

    /**
     * Realiza a exclusão da entidade informada no banco de dados
     *
     * @param id ID da entidade a ser excluído
     */
    void excluir(ID id);

}
