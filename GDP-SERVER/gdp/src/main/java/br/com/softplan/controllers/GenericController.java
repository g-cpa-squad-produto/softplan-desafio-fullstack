package br.com.softplan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.softplan.services.IGenericService;

public class GenericController<T, ID>  implements IGenericController<T, ID> {

	@Autowired
	private IGenericService<T, ID> service;
	
	@GetMapping
	@Override
	public List<T> findAll() {
		return this.service.findAll();
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	@Override
	public Optional<T> findById(ID id) {
		return this.service.findById(id);
	}


	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		
	}

}
