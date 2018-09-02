package br.com.softplan.desafiojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafiojava.entity.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

}
