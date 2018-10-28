package br.com.softplan.processos.service;

import java.util.List;

import br.com.softplan.processos.model.Permissao;

public interface ServicoPermissao {

    /**
     * Retorna a lista de permissões
     * 
     * @return List<Permissao>
     */
    public List<Permissao> selecionarTodas();
}
