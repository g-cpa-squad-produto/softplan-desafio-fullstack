package com.teste.magnum.gerenciadorprocessosapi.service;

import com.teste.magnum.gerenciadorprocessosapi.exception.ProcessoException;
import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.NovoUsuarioProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ParecerDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ProcessoDTO;

import java.util.List;

public interface IProcessoService {

    List<ProcessoDTO> findAll() throws NotFoundException;
    ProcessoDTO save(ProcessoDTO processoDTO);
    NovoUsuarioProcessoDTO addUsers(NovoUsuarioProcessoDTO novoUsuarioProcessoDTO) throws ProcessoException, UsuarioException;
    ProcessoDTO addParecer(Long idProcesso, ParecerDTO parecerDTO) throws ProcessoException;
    ProcessoDTO getProcesso(Long idProcesso) throws ProcessoException, NotFoundException;

}
