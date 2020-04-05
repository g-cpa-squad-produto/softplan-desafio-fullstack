package com.softplan.processesapi.application.controllers;

import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/anything")
public interface IRestController<T> {

    @GetMapping("/")
    public List<T> getAll();

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id) throws ResourceNotFoundException;

    @PostMapping()
    public T post(@RequestBody T t)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    @PutMapping()
    public T put(@RequestBody T t) throws ResourceNotFoundException;

    @DeleteMapping()
    public void delete(@PathVariable Long id);
}
