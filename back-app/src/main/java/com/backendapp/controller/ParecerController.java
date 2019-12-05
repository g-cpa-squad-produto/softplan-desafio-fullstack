package com.backendapp.controller;

import com.backendapp.dto.ParecerDTO;
import com.backendapp.model.Parecer;
import com.backendapp.model.Processo;
import com.backendapp.repository.ParecerRepository;
import com.backendapp.repository.ProcessoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/pareceres"})
public class ParecerController {
    private ParecerRepository repository;
    private ProcessoRepository processoRepository;


    ParecerController(ParecerRepository parecerRepository,ProcessoRepository processoRepository) {
        this.repository = parecerRepository;
        this.processoRepository = processoRepository;

    }




    @PostMapping
    public ResponseEntity create(@RequestBody ParecerDTO parecerDTO){
     return processoRepository.findById(parecerDTO.getIdProcesso())
                .map(record -> {
                    
                    Parecer parecer = new Parecer();
                    parecer.setTexto(parecerDTO.getTexto());
                    record.setParecer(parecer);
                    //repository.save(parecer);
                    processoRepository.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

                ;





        return repository.save(parecer);
    }



}
