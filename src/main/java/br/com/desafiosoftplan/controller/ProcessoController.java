package br.com.desafiosoftplan.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.desafiosoftplan.model.Parecer;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.model.Usuario;
import br.com.desafiosoftplan.security.UsuarioSistema;
import br.com.desafiosoftplan.service.ParecerService;
import br.com.desafiosoftplan.service.ProcessoService;
import br.com.desafiosoftplan.service.UsuarioService;


/**
 * Classe controladora do Processo
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Controller
public class ProcessoController
{
   @Autowired
   private ProcessoService processoService;

   @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private ParecerService parecerService;


   /**
    * Busca todos os processos cadastrados
    * @return
    */
   @GetMapping("/")
   public ModelAndView findAll(){
      ModelAndView modelAndView = new ModelAndView("/processo/processo");
      modelAndView.addObject("processos", processoService.listarTodos());
      return modelAndView;
   }

   /**
    * Adiciona um novo processo
    * @param processo
    * @return
    */
   @GetMapping("/add")
   public ModelAndView add(Processo processo) {

      ModelAndView modelAndView = new ModelAndView("/processo/adicionaprocesso");
      modelAndView.addObject("processo", processo);
      return modelAndView;
   }


   /**
    * Redireciona para a pagina de edição do processo
    * @param id
    * @return
    */
   @GetMapping("/edit/{id}")
   public ModelAndView edit(@PathVariable("id") Long id) {
      return add(processoService.buscarPorId(id));
   }


   /**
    * Salva um processo
    * @param processo
    * @param result
    * @return
    */
   @PostMapping("/save")
   public ModelAndView save(@Valid Processo processo, BindingResult result) {

      if(result.hasErrors()) {
         return add(processo);
      }

      processoService.salvar(processo);
      return findAll();
   }

}
