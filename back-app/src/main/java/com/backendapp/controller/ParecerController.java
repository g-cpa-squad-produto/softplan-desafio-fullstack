package com.backendapp.controller;

import com.backendapp.dto.ParecerDTO;
import com.backendapp.model.Parecer;
import com.backendapp.model.Processo;
import com.backendapp.repository.ParecerRepository;
import com.backendapp.repository.ProcessoRepository;
import com.backendapp.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/pareceres"})
public class ParecerController {
    private ParecerRepository repository;
    private ProcessoRepository processoRepository;
    private UsuarioRepository usuarioRepository;



    ParecerController(ParecerRepository parecerRepository,ProcessoRepository processoRepository, UsuarioRepository usuarioRepository) {
        this.repository = parecerRepository;
        this.processoRepository = processoRepository;
        this.usuarioRepository = usuarioRepository;

    }




    @PostMapping
    public ResponseEntity create(@RequestBody ParecerDTO parecerDTO){
     return processoRepository.findById(parecerDTO.getIdProcesso())
                .map(record -> {

                    Parecer parecer = new Parecer();
                    parecer.setDataCriacao(LocalDate.now());
                    parecer.setTexto(parecerDTO.getTexto());
                    parecer.setUsuario(usuarioRepository.findById(parecerDTO.getIdUsuario()).get());
                    repository.save(parecer);

                    record.setParecer(parecer);
                    processoRepository.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
     }



}
