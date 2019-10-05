package com.isadora.backendapi.services;

import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.exceptions.UsuarioException;
import com.isadora.backendapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario){
        try {
            usuario.setEmail(usuario.getEmail());

            return usuarioRepository.save(usuario);
        }catch (Exception e){
            throw new UsuarioException("Usuário com email '"+usuario.getEmail()+"' já existe.");
        }
    }

    public @Valid Usuario update(Long id, Usuario usuarioAlterado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setStatus(usuarioAlterado.getStatus());
            usuario.setTipo(usuarioAlterado.getTipo());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("UsuarioException não encontrado!"));
    }


    public Usuario findByEmail(String email) {
        Usuario project = usuarioRepository.findByEmail(email);

        if (project == null) {
            throw new UsuarioException("UsuarioException com email '"+email+"' não existe.");
        } else {
            return project;
        }
    }

    public Iterable<Usuario> findAllProjects(){
        return usuarioRepository.findAll();
    }

    public void deleteProjectById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario==null) {
            throw new UsuarioException("UsuarioException não existe.");
        }
        usuarioRepository.deleteById(id);
    }


}
