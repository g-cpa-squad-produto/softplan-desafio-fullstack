package com.isadora.backendapi.services;

import com.isadora.backendapi.domain.Parecer;
import com.isadora.backendapi.domain.Processo;
import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.exceptions.ProcessoException;
import com.isadora.backendapi.repositories.ParecerRepository;
import com.isadora.backendapi.repositories.ProcessoRepository;
import com.isadora.backendapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParecerService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ParecerRepository parecerRepository;

    @Autowired
    private ProcessoRepository processoRepository;


    public Parecer findByProcesso(Processo processo){
        return this.parecerRepository.findByProcesso(processo);
    }


    public Parecer save(Parecer parecer, Long usuarioId, Long processoId){
        try {
            Optional<Processo> processo = processoRepository.findById(processoId);
            if(processo.isPresent()) {
                parecer.setProcesso(processo.get());
            }
            Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
            if(usuario.isPresent()) {
                parecer.setUsuario(usuario.get());
            }
            return parecerRepository.save(parecer);
        }catch (Exception e){
            throw new ProcessoException("Processo nao existe");
        }
    }

    public Iterable<Parecer> findAllPareceres() {
        return parecerRepository.findAll();
    }

    public Parecer findParecerByProcessoId(Long idProcesso) {
        Optional<Processo> processo = processoRepository.findById(idProcesso);
        if (!processo.isPresent()) {
            throw new ProcessoException("Processo com ID '" + idProcesso + "' não existe.");
        }
        return processo.get().getParecer();
    }

    public Iterable<Parecer>  findByUsuario(Long idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (!usuario.isPresent()) {
            throw new ProcessoException("Usuario com ID '" + idUsuario + "' não existe.");
        }
        return usuario.get().getParecer();
    }


}
