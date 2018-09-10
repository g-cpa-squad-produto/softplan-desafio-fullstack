package br.com.softplan.controller;

import br.com.softplan.domain.Processousuario;
import br.com.softplan.service.ProcessousuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("processos/{processoId}/processousuarios")
public class ProcessousuarioController {

    @Autowired
    private ProcessousuarioService processousuarioService;

    @GetMapping("/listar")
    public ModelAndView listar(@PathVariable("processoId") long processoId, ModelMap model) {
        model.addAttribute("processousuario", processousuarioService.recuperarPorProcesso(processoId));
        model.addAttribute("processoId", processoId);
        return new ModelAndView("/processousuario/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("processousuario") Processousuario processousuario, @PathVariable("processoId") long processoId) {
        return "/processousuario/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("processoId") long processoId,@PathVariable("usuarioId") long usuarioId, @Valid @ModelAttribute("processousuario") Processousuario processousuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/processousuario/add";
        }

        processousuarioService.salvar(processousuario, processoId, usuarioId);
        attr.addFlashAttribute("mensagem", "Usuario adicionado ao processo com sucesso.");
        return "redirect:/processousuario/" + processoId + "/processousuario/listar";
    }

}
