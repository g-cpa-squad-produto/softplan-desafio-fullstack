package br.com.softplan.processos.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.processos.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {
    /**
     * Retorna o usuário de acordo com o e-mail
     * 
     * @param email
     * @return Usuario
     */
    public Usuario findByEmail(String email);
}
