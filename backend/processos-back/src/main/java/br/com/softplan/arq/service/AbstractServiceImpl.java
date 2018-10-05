package br.com.softplan.arq.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractServiceImpl<T, ID extends Serializable> implements AbstractService<T, ID>{
	
	@Override
	public T createOrUpdate(T obj) {
		return getRepository().save(obj);
	}

	public T findById(ID id) {
		return getRepository().findOne(id);
	}

	public void delete(ID id) {
		getRepository().delete(id);
	}

	public Page<T> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return getRepository().findAll(pages);
	}
	
	public abstract PagingAndSortingRepository<T, ID> getRepository();

}
