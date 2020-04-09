package com.teste.magnum.gerenciadorprocessosapi.service;

import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> findAll() throws NotFoundException;
    UsuarioDTO save(UsuarioDTO usuarioDTO) throws UsuarioException;
    UsuarioDTO edit(Long id) throws UsuarioException;
    UsuarioDTO findById(Long id) throws NotFoundException;
    void delete(UsuarioDTO usuarioDTO);
    void deleteById(Long id) throws UsuarioException;
}
