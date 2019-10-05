package com.isadora.backendapi.controller;

import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //cria
    //altera
    //busca por id
    //remove
    //lista todos
}
