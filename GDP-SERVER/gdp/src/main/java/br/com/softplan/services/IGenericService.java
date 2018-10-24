package br.com.softplan.services;

import java.util.List;
import java.util.Optional;

public interface IGenericService <T, ID>{
	
	public Optional<T> findById(ID id);
	public List<T> findAll();
	public void deleteById(ID id);
	public void save(T t);
	
}
