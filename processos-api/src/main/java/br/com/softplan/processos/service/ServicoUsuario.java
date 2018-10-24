package br.com.softplan.processos.service;

import br.com.softplan.processos.model.Usuario;

public interface ServicoUsuario {

    /**
     * Retorna a lista de todos os usuários
     * 
     * @return Iterable<Usuario>
     */
    public Iterable<Usuario> selecionarTodos();

    /**
     * Retorna o usuário de acordo com o ID
     * 
     * @param id
     * @return Usuario
     */
    public Usuario selecionarUsuarioPorId(Long id);

    /**
     * Adiciona um usuário
     * 
     * @param usuario
     */
    public Usuario adicionarUsuario(Usuario usuario);

    /**
     * Exclui o usuário de acordo com o ID
     * 
     * @param id
     */
    public void excluirUsuario(Long id);
}
