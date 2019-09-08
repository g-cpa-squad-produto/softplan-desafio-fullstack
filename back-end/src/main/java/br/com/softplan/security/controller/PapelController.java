package br.com.softplan.security.controller;

import br.com.softplan.security.entity.Papel;
import br.com.softplan.security.repository.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/papeis")
public class PapelController {

    @Autowired
    private PapelRepository papelRepository;

    @GetMapping
    public List<Papel> buscar(){
        return papelRepository.findAll();
    }
}
