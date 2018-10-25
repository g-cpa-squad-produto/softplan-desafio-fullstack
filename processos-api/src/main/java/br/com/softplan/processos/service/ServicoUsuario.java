package br.com.softplan.processos.service;

import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.model.Usuario;

public interface ServicoUsuario {

    /**
     * Retorna a lista de todos os usuários
     * 
     * @return Iterable<Usuario>
     * @throws GenericException
     */
    public Iterable<Usuario> selecionarTodos() throws GenericException;

    /**
     * Retorna o usuário de acordo com o ID
     * 
     * @param id
     * @return Usuario
     * @throws GenericException
     */
    public Usuario selecionarUsuarioPorId(Long id) throws GenericException;

    /**
     * Adiciona um novo usuário
     * 
     * @param usuario
     * @throws GenericException
     */
    public Usuario adicionarUsuario(Usuario usuario) throws GenericException;

    /**
     * Atualiza os dados do usuário
     * 
     * @param id
     * @param usuario
     * @return Usuario
     * @throws GenericException
     */
    public Usuario atualizarUsuario(Long id, Usuario usuario) throws GenericException;

    /**
     * Exclui o usuário de acordo com o ID
     * 
     * @param id
     * @throws GenericException
     */
    public void excluirUsuario(Long id) throws GenericException;
}
