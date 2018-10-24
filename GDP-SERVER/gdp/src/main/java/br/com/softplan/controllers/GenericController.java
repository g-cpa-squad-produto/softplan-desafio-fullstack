package br.com.softplan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.softplan.services.IGenericService;

public abstract class GenericController<T, ID> implements IGenericController<T, ID> {

	@Autowired
	private IGenericService<T, ID> service;

	@GetMapping
	@Override
	public List<T> findAll() {
		return this.service.findAll();
	}

	@CrossOrigin
	@GetMapping("/{id}")
	@Override
	public Optional<T> findById(@PathVariable ID id) {
		return this.service.findById(id);
	}

	@Override
	@CrossOrigin
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable ID id) {
		this.service.deleteById(id);
	}

	@Override
	@PostMapping
	public void save(@RequestBody T t) {
		this.service.save(t);
	}

	@Override
	@PutMapping
	public void update(@RequestBody T t) {
		this.service.save(t);
	}

}
