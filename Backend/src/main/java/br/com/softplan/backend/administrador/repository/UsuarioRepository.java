package br.com.softplan.backend.administrador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import br.com.softplan.backend.administrador.model.UsuarioModel;

@Component
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {
}
