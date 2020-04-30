package br.com.softplan.backend.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {
}
