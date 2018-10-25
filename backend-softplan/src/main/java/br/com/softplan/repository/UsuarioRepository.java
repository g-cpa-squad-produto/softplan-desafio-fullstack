package br.com.softplan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	Usuario findByEmail(String email);

}
