package com.softplan.processesapi.application.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/anything")
public interface IRestController<T> {

    @GetMapping("/")
    public List<T> getAll();

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id);

    @PostMapping()
    public T post(@RequestBody T t);

    @PutMapping()
    public T put(@RequestBody T t);

    @DeleteMapping()
    public Map<String, Boolean> delete(@PathVariable Long id);
}
