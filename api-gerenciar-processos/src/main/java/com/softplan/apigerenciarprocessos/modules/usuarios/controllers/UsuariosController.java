package com.softplan.apigerenciarprocessos.modules.usuarios.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

  @GetMapping
  public String listar() {
    return "Listar usuarios";
  }
}