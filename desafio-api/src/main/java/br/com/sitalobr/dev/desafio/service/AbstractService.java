package br.com.sitalobr.dev.desafio.service;

import br.com.sitalobr.dev.desafio.entity.InterfaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Classe abstrata responsável por reunir comportamentos em comum de todos os serviços
 * @param <T> Tipo da entidade principal processado pelo serviço que a extender
 * @param <ID> Tipo do ID da entidade principal
 */
public abstract class AbstractService<T extends InterfaceEntity<ID>, ID> {
    public abstract CrudRepository<T, ID> getRepository();

    /**
     * Implementação padrão do método para recuperação de um recurso a partir do ID, realizando verificação de existência
     * @param id ID do recurso a ser pesquisado
     * @return Objeto relacionado a entidade especificada
     */
    public T findById(ID id) {
        return this.getRepository().findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "O recurso solicitado não foi encontrado."));
    }
}
