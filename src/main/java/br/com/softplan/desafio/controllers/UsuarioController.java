package br.com.softplan.desafio.controllers;

import br.com.softplan.desafio.bo.UsuarioBO;
import br.com.softplan.desafio.models.Perfil;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.models.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioBO usuarioBO;

    @GetMapping
    public List<UsuarioDTO> search() {
        return usuarioBO.search();
    }

    @PostMapping
    public Usuario salva(@Valid @RequestBody Usuario usuario) {
        return usuarioBO.salva(usuario);
    }

    @PutMapping
    public Usuario atualiza(@Valid @RequestBody Usuario usuario) {
        return usuarioBO.atualiza(usuario);
    }

    @DeleteMapping("/{codigo}")
    public Usuario deleta(@PathVariable("codigo") Long codigo) {
        return usuarioBO.deleta(codigo);
    }

    @GetMapping("/perfil")
    public List<Perfil> getPerfis() {
        return Arrays.asList(Perfil.values());
    }

}