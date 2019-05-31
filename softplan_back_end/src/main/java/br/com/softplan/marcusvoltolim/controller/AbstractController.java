package br.com.softplan.marcusvoltolim.controller;

import br.com.softplan.marcusvoltolim.model.support.EntityLongId;
import br.com.softplan.marcusvoltolim.service.AbstractService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;

public abstract class AbstractController<T extends EntityLongId> {
	
	private AbstractService<T> abstractService;
	
	AbstractController(AbstractService<T> abstractService) {
		Assert.notNull(abstractService, "service obrigatorio");
		this.abstractService = abstractService;
	}
	
	@GetMapping()
	public ResponseEntity<List<T>> findAll() {
		List<T> entidades = abstractService.findAll();
		if (Objects.isNull(entidades)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(entidades, HttpStatus.OK);
	}
	
	@GetMapping("findAllBy")
	public ResponseEntity<List<T>> findAllBy(@RequestParam String filtro) {
		List<T> usuario = abstractService.findAllBy(tratarFiltroLike(filtro));
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	
	private String tratarFiltroLike(String filtro) {
		return "%" + filtro + "%";
	}
	
	@PostMapping()
	public ResponseEntity<T> create(@RequestBody String content) {
		T entidadeCriada = abstractService.create(content);
		if (entidadeCriada == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(entidadeCriada, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable Long id, @RequestBody String content) {
		T entidadeAtualizada = abstractService.update(id, content);
		if (entidadeAtualizada == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(entidadeAtualizada, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			abstractService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
