package com.teste.magnum.gerenciadorprocessosapi.service.impl;

import com.teste.magnum.gerenciadorprocessosapi.domain.Parecer;
import com.teste.magnum.gerenciadorprocessosapi.domain.Processo;
import com.teste.magnum.gerenciadorprocessosapi.domain.Usuario;
import com.teste.magnum.gerenciadorprocessosapi.exception.ProcessoException;
import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.StatusProcessoEnum;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.NovoUsuarioProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ParecerDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.UsuarioProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.repository.ProcessoRepository;
import com.teste.magnum.gerenciadorprocessosapi.repository.UsuarioRepository;
import com.teste.magnum.gerenciadorprocessosapi.service.IProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProcessoService implements IProcessoService {

    private final ProcessoRepository processoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProcessoService(ProcessoRepository processoRepository, UsuarioRepository usuarioRepository) {
        this.processoRepository = processoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ProcessoDTO> findAll() throws NotFoundException {
        List<Processo> processos =  processoRepository.findAll();
        if(processos.isEmpty()) {
            throw new NotFoundException(MessageFormat.format("Nenhum processo encontrado.", null));
        }
        return processos.stream().map(this::buildProcessoDTO).collect(Collectors.toList());
    }

    @Override
    public ProcessoDTO save(ProcessoDTO processoDTO) {
        return buildProcessoDTO(processoRepository.save(buildNovoProcesso(processoDTO)));
    }

    @Override
    public NovoUsuarioProcessoDTO addUsers(NovoUsuarioProcessoDTO novoUsuarioProcessoDTO) throws ProcessoException, UsuarioException {
        validaUsuariosParecer(novoUsuarioProcessoDTO);
        Optional<Processo> processo = processoRepository.getById(novoUsuarioProcessoDTO.getIdProcesso());
        if(processo.isPresent()) {
            novoUsuarioProcessoDTO.getUsuariosVinculados().forEach(
                    u -> processo.get().getUsuariosVinculados().add(u));
            processoRepository.save(processo.get());
            return novoUsuarioProcessoDTO;
        } else {
            throw new ProcessoException(MessageFormat.format("Erro ao tentar incluir um novo usuário ao processo {0}.",
                    novoUsuarioProcessoDTO.getIdProcesso()));
        }
    }

    @Override
    public ProcessoDTO addParecer(Long idProcesso, ParecerDTO parecerDTO) throws ProcessoException {
        Optional<Processo> processo = processoRepository.getById(idProcesso);
        if(processo.isPresent()) {
            processo.get().setParecer(buildNovoParecer(parecerDTO));
            processo.get().setStatusProcesso(StatusProcessoEnum.PARECER_ADICIONADO);
            return buildProcessoDTO(processoRepository.save(processo.get()));
        }
        throw new ProcessoException(MessageFormat.format("Erro ao tentar incluir um novo parecer ao processo {0}.",
                idProcesso));
    }

    @Override
    public ProcessoDTO getProcesso(Long idProcesso) throws ProcessoException, NotFoundException {
        return buildProcessoDTO(processoRepository.getById(idProcesso).orElseThrow(() -> new NotFoundException(MessageFormat.format("Nenhum processo encontrado.", null))));
    }

//    public UsuarioProcessoDTO getUsuarioProcessoByCPF(String cpf) {
//        Optional<Usuario> usuario = usuarioRepository.getUsuarioByCpf(cpf);
//        if(usuario.isPresent()) {
//            return buildProcessoDTO()
//        }
//
//    }

    private Processo buildNovoProcesso(ProcessoDTO processoDTO) {
        return Processo.builder()
                .descricao(processoDTO.getDescricao())
                .usuariosVinculados(processoDTO.getUsuariosVinculados())
                .dataRegistro(LocalDateTime.now())
                .statusProcesso(StatusProcessoEnum.PENDENTE_PARECER)
                .build();
    }

    private void validaUsuariosParecer(NovoUsuarioProcessoDTO novoUsuarioProcessoDTO) throws UsuarioException {
        for (String u : novoUsuarioProcessoDTO.getUsuariosVinculados()) {
            if(!usuarioRepository.getUsuarioByCpf(u).isPresent()) {
                throw new UsuarioException(MessageFormat.format("Usuario não cadastrado no sistema.", null));
            }
        }
    }

    private ProcessoDTO buildProcessoDTO(Processo processo) {
        List<Usuario> usuarios = new ArrayList<>();
        Collection<String> usuariosProcesso = new ArrayList<>();
        processo.getUsuariosVinculados().forEach(s -> usuarioRepository.getUsuarioByCpf(s).ifPresent(usuarios::add));
        usuarios.forEach(usuario -> usuariosProcesso.add(usuario.getNome()));
        return ProcessoDTO.builder()
                .id(processo.getId())
                .descricao(processo.getDescricao())
                .usuariosVinculados(usuariosProcesso)
                .dataRegistro(processo.getDataRegistro())
                .statusProcesso(processo.getStatusProcesso())
                .parecer(buildParecerDTO(processo.getParecer()))
                .build();
    }

    private Parecer buildNovoParecer(ParecerDTO parecerDTO) {
        return Parecer.builder()
                .descricao(parecerDTO.getDescricao())
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    private ParecerDTO buildParecerDTO(Parecer parecer) {
        if(parecer != null) {
            return ParecerDTO.builder()
                    .id(parecer.getId())
                    .descricao(parecer.getDescricao())
                    .dataRegistro(parecer.getDataRegistro())
                    .build();
        }
        return null;
    }
}
