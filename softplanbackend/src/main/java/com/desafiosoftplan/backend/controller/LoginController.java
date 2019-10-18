package com.desafiosoftplan.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/login")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LoginController {

}
