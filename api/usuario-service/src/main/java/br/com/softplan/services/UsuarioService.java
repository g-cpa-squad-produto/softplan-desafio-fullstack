package br.com.softplan.services;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public String buscarUsuario(Integer codigo) {
        return "Usuario poc Softplan: " + codigo;
    }
}
