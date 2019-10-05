package com.isadora.backendapi.controller;

import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processo")
@CrossOrigin(origins = "*")
public class ProcessoController {

    @Autowired
    private ProcessoService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //criar
    //busca por id
    //lista todos
    //busca por parecer em branco

}
