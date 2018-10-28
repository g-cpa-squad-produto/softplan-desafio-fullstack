package br.com.softplan.processos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.processos.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    /**
     * Retorna o usuário de acordo com o e-mail
     * 
     * @param email
     * @return Usuario
     */
    public Usuario findByEmail(String email);
}
