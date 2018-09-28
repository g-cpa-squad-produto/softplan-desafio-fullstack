package br.com.desafiosoftplan.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import br.com.desafiosoftplan.vo.ProcessoVO;

/**
 * Classe controladora responsavel pelo Parecer
 *
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Controller
public class ParecerController
{

   @Autowired
   private ProcessoService processoService;

   @Autowired
   private UsuarioService usuarioServiceService;


   @Autowired
   private ParecerService parecerService;


   private Processo processo;

   private ProcessoVO processoVO;

   /**
    * Redireciona para atribuição de usuário ao processo
    * @param id
    * @return
    */
   @GetMapping("/addparecer/{id}")
   public ModelAndView addparecer(@PathVariable("id") Long id) {


      processo = processoService.buscarPorId(id);

      ProcessoVO processoVO = new ProcessoVO();
      processoVO.setProcesso(processo);

      List<Usuario> usuarios = usuarioServiceService.buscaUsuariosPassiveisParecer();
      ModelAndView modelAndView = new ModelAndView("/processo/atribuir_usuario_processo");

      modelAndView.addObject("processoVO", processoVO);
      modelAndView.addObject("usuarios", usuarios);
      modelAndView.addObject("listaParecer", processo.getParecers());
      return modelAndView;

   }


   /**
    * Atribui usuário ao processo
    * @param processoVO
    * @param result
    * @return
    */
   @PostMapping("/saveparecer")
   public ModelAndView save(@Valid ProcessoVO processoVO, BindingResult result) {

      Parecer parecer = new Parecer();
      parecer.setUsuario(processoVO.getUsuario());
      parecer.setProcesso(processo);
      parecer.setIndicadorParecer(Boolean.FALSE);
      List<Parecer> listaParecer = new ArrayList<>();
      listaParecer.add(parecer);

      processoVO.getProcesso().setParecers(listaParecer);

      processoService.salvar(processoVO.getProcesso());

      return addparecer(processo.getId());
   }


   /**
    * Redireciona para a página de finalização do processo
    * @param id
    * @return
    */
   @GetMapping("/incluirdescricao/{id}")
   public ModelAndView incluirDescricaoParecer(@PathVariable("id") Long id) {

      Parecer parecer = parecerService.buscarPorId(id);
      ModelAndView modelAndView = new ModelAndView("/processo/incluir_descricao_parecer");
      modelAndView.addObject("parecer", parecer);
      return modelAndView;

   }

   /**
    * Busca processo pendentes do usuário logado
    * @return
    */
   @GetMapping("/listarprocessos")
   public ModelAndView listarProcessos()
   {
      Usuario usuario = new Usuario();
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UsuarioSistema)
      {
         Long id = ((UsuarioSistema) principal).getId();
         usuario.setId(id);
      }

      List<Parecer> listaParecer = parecerService.buscarProcessosPendentes(usuario);


      ModelAndView modelAndView = new ModelAndView("/processo/listar_processo_usuario");
      modelAndView.addObject("listaParecer", listaParecer);
      return  modelAndView;
   }

   /**
    * Finaliza o processo
    * @param parecer
    * @param result
    * @return
    */
   @PostMapping("/salvadescricao")
   public ModelAndView save(@Valid Parecer parecer, BindingResult result) {

      parecer.setIndicadorParecer(Boolean.TRUE);
      parecerService.salvar(parecer);
      return listarProcessos();
   }


}
