package br.com.softplan.processos.service;

import br.com.softplan.processos.model.Usuario;

public interface ServicoUsuario {

    /**
     * Retorna a lista de todos os usuários
     * 
     * @return Iterable<Usuario>
     */
    public Iterable<Usuario> selecionarTodos();
}
