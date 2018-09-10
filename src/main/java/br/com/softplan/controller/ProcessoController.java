package br.com.softplan.controller;

import br.com.softplan.domain.Processo;
import br.com.softplan.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("processo")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){

        model.addAttribute( "processoList", processoService.recuperar());
        return new ModelAndView( "/processo/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("processo") Processo processo){

        try{
            return "/processo/add";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("processo") Processo processo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "/processo/add";
        }

        processoService.salvar(processo);
        attr.addFlashAttribute("mensagem", "Processo salvo com sucesso.");
        return "redirect:/processo/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {

        Processo processo = processoService.recuperarPorId(id);
        model.addAttribute("processo", processo);
        return new ModelAndView("/processo/add", model);

    }

    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("processo")
                                    Processo processo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/processo/add";
        }

        processoService.atualizar(processo);
        attr.addFlashAttribute("mensagem", "Processo atualizado com sucesso.");
        return "redirect:/processo/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {

        processoService.excluir(id);
        attr.addFlashAttribute("mensagem", "Processo excluído com sucesso.");
        return "redirect:/processo/listar";
    }


}
