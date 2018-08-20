package com.processos.luiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.processos.luiz.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByLogin(String login);
	Usuario findByCodigo(long codigo);
}
	