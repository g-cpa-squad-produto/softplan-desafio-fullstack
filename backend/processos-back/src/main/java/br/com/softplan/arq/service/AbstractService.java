package br.com.softplan.arq.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;

/**
 * Interface abstrata para services do projeto
 * @author Marco
 *
 * @param <T>
 * @param <ID>
 */
public interface AbstractService<T, ID extends Serializable> {
	
	/**Cria ou atualiza uma entidade*/
	public T createOrUpdate(T obj);

	/**Busca pelo id*/
	public T findById(ID id);

	/**Remove uma entidade*/
	public void delete(ID id);

	/**Recupera todas com paginação*/
	public Page<T> findAll(int page, int count);
}
