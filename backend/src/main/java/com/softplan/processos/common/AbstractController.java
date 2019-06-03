package com.softplan.processos.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

public abstract class AbstractController<T, ID> {

    @Autowired
    protected PagingAndSortingRepository<T, ID> repository;

    @Autowired
    protected Validator validator;

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") ID id) {
        T entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<T>> findAllPaged(Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T entity) {
        validate(entity);
        T entitySaved = repository.save(entity);
        return new ResponseEntity(entitySaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") ID id, @RequestBody T entity) {
        repository.findById(id).orElseThrow(EntityNotFoundException::new);
        validate(entity);
        T entityUpdated = repository.save(entity);
        return new ResponseEntity(entityUpdated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity remove(@PathVariable("id") ID id) {
        T entityToRemove = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(entityToRemove);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    protected void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
