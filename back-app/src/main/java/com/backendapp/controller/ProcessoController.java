package com.backendapp.controller;

import com.backendapp.model.Processo;
import com.backendapp.repository.ProcessoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/processos"})
public class ProcessoController {
    private ProcessoRepository repository;

    ProcessoController(ProcessoRepository processoRepository) {
        this.repository = processoRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   processoById(@PathVariable long id){
        return repository.findById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Processo create(@RequestBody Processo processo){
        return repository.save(processo);
    }


    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
