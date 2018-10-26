package br.com.softplan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.models.Proccess;


@RestController
@RequestMapping("/process")
public class ProcessController  extends GenericController<Proccess, Long> {

}

