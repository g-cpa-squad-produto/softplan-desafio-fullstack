package br.com.softplan.processos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processos.dao.UsuarioDAO;
import br.com.softplan.processos.model.Usuario;

@Service
public class ServicoUsuarioImpl implements ServicoUsuario {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Iterable<Usuario> selecionarTodos() {
	return usuarioDAO.findAll();
    }

}
