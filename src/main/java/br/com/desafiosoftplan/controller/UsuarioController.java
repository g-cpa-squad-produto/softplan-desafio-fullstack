package br.com.desafiosoftplan.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.desafiosoftplan.dominio.TipoUsuario;
import br.com.desafiosoftplan.model.Usuario;
import br.com.desafiosoftplan.service.UsuarioService;
import br.com.desafiosoftplan.vo.UsuarioVO;


/**
 * Classe controladora responsável pelo fluxo de Usuário
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Controller
public class UsuarioController
{
   @Autowired
   private UsuarioService usuarioService;


   /**
    * Listagem de todos os usuários cadastrados no sistema
    * @return
    */
   @GetMapping("/usuario")
   public ModelAndView findAll(){
      ModelAndView modelAndView = new ModelAndView("/usuario/usuario");
      modelAndView.addObject("usuarios", usuarioService.listarTodos());
      return modelAndView;
   }

   /**
    * Redireciona para o cadastro de um novo usuário
    * @param usuarioVO
    * @return
    */
   @GetMapping("/usuario/add/")
   public ModelAndView add(UsuarioVO usuarioVO) {
      ModelAndView modelAndView = new ModelAndView("/usuario/adicionausuario");
      modelAndView.addObject("usuarioVO", usuarioVO);
      return modelAndView;
   }


   /**
    * Redireciona para a edição de um usuario
    * @param id
    * @return
    */
   @GetMapping("/usuario/edit/{id}")
   public ModelAndView edit(@PathVariable("id") Long id) {
      Usuario usuario = usuarioService.buscarPorId(id);

      UsuarioVO vo = new UsuarioVO();
      vo.setId(usuario.getId());
      vo.setNome(usuario.getNome());
      vo.setLogin(usuario.getLogin());
      vo.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

      return add(vo);
   }

   /**
    * Exclui um usuário selecionado
    * @param id
    * @return
    */
   @GetMapping("/usuario/delete/{id}")
   public ModelAndView delete(@PathVariable("id") Long id) {

      usuarioService.excluir(id);
      return findAll();
   }

   /**
    * Salva um usuário
    * @param usuarioVO
    * @param result
    * @return
    */
   @PostMapping("/usuario/save")
   public ModelAndView save(@Valid UsuarioVO usuarioVO, BindingResult result) {

      Usuario usuario = null;
      if(usuarioVO.getId() == null){
         usuario = new Usuario();
         usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioVO.getSenha()));
      } else {
         usuario = usuarioService.buscarPorId(usuarioVO.getId());
      }

      usuario.setTipoUsuario(TipoUsuario.get(usuarioVO.getTipoUsuario()));
      usuario.setNome(usuarioVO.getNome());
      usuario.setLogin(usuarioVO.getLogin());

      if(result.hasErrors()) {
         return add(usuarioVO);
      }

      usuarioService.salvar(usuario);
      return findAll();
   }
}
