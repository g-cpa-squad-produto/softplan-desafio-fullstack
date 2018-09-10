package br.com.softplan.controller;

import br.com.softplan.domain.Usuarios;
import br.com.softplan.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){

        model.addAttribute( "usuariosList", usuariosService.recuperar());
        return new ModelAndView( "/usuarios/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("usuarios") Usuarios usuarios){

        try{
            return "/usuarios/add";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuarios") Usuarios usuarios, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "/usuarios/add";
        }

        usuariosService.salvar(usuarios);
        attr.addFlashAttribute("mensagem", "Usuario salvo com sucesso.");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {

        Usuarios usuarios = usuariosService.recuperarPorId(id);
        model.addAttribute("usuarios", usuarios);
        return new ModelAndView("/usuarios/add", model);

    }

    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("usuarios")
                                    Usuarios usuarios, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/usuarios/add";
        }

        usuariosService.atualizar(usuarios);
        attr.addFlashAttribute("mensagem", "Usuario atualizado com sucesso.");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {

        usuariosService.excluir(id);
        attr.addFlashAttribute("mensagem", "Usuario excluído com sucesso.");
        return "redirect:/usuarios/listar";
    }

}
