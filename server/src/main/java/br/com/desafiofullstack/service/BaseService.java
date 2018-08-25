/**
 * 
 */
package br.com.desafiofullstack.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Delano Jr
 *
 */
public interface BaseService<T, ID> extends Serializable {

	public Optional<T> save(T t);

	public Optional<List<T>> saveAll(List<T> ts);

	public Optional<List<T>> findAll();

	public Optional<T> findOne(ID id);

	public void delete(T t);

	public void deleteAll(List<T> ts);

	public void deleteById(ID id);

}
