package br.com.softplan.controller;

import br.com.softplan.domain.LoginForm;
import br.com.softplan.domain.Usuarios;
import br.com.softplan.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UsuariosService usuariosService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(){

        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        Usuarios usuario = usuariosService.recuperarPorUsername(loginForm.getUsername());


        if("admin".equals(username) && "admin".equals(password)){
            model.addAttribute("admin", true);
            model.addAttribute("triador", true);
            return "/home";
        }else if(usuario != null){

            if(usuario.getPassword().equals(password)){
                model.addAttribute("admin", false);
                model.addAttribute("triador", trataTipoUsuario(usuario));
                return "/home";
            }else{
                model.addAttribute("invalidCredentials", true);
                return "/login";
            }
        }

        model.addAttribute("invalidCredentials", true);
        return "/login";

    }

    private boolean trataTipoUsuario(Usuarios usuarios){

        if(usuarios.getTipousuario().equals("ut")){
            return true;
        }else{
            return false;
        }

    }

}
