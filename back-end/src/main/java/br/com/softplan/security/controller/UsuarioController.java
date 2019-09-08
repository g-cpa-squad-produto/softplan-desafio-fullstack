package br.com.softplan.security.controller;

import br.com.softplan.security.business.UsuarioService;
import br.com.softplan.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> buscar(){
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable("id") Long id){
        return service.buscar(id);
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario editar(@PathVariable("id") Long id,  @RequestBody Usuario usuario){
        return service.editar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id){
        service.excluir(id);
    }
}
