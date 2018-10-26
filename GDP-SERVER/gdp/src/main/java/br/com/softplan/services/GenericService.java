package br.com.softplan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<T, ID> implements IGenericService<T, ID> {

	@Autowired
	private JpaRepository<T, ID> repository;

	@Override
	public Optional<T> findById(ID id) {
		return this.repository.findById(id);
	}

	@Override
	public List<T> findAll() {
		return this.repository.findAll();
	}

	@Override
	public void deleteById(ID id) {
		this.repository.deleteById(id);
	}

	@Override
	public void save(T t) {
		this.repository.save(t);
	}
	
	@Override
	public void update(T t) {
		this.repository.save(t);
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}
	
}
