package br.com.desafiosoftplan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de login
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Controller
public class HomeController
{
   @GetMapping("/login")
   public String login() {
      return "login";
   }
}
