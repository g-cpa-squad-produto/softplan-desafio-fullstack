package br.com.ramonbarros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.ramonbarros.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepositoryImplementation<Usuario,Long> {

	Optional<Usuario> findByLoginIgnoreCase(String login);
	
}
