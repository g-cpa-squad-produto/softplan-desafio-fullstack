package br.com.softplan.backend.administrador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.com.softplan.backend.administrador.model.UsuarioModel;
import br.com.softplan.backend.administrador.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	private final MongoTemplate mongoTemplate;

	public UsuarioService(UsuarioRepository usuarioRepository, MongoTemplate mongoTemplate) {
		this.usuarioRepository = usuarioRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public UsuarioModel saveUsuario(UsuarioModel usuarioModel){
		return usuarioRepository.save(usuarioModel);
	}

	public UsuarioModel updateUsuario(UsuarioModel usuarioModel){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(usuarioModel.getUsurioId()));
		Update usuario = new Update();
		usuario.set("nome", usuarioModel.getNome());
		usuario.set("email", usuarioModel.getEmail());
		usuario.set("dataNascimento", usuarioModel.getDataNascimento());
		return mongoTemplate.findAndModify(query, usuario, UsuarioModel.class);
	}

	public void deleteUsuario(String usuarioId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(usuarioId));
		mongoTemplate.remove(query, UsuarioModel.class);
	}

	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}

	public Optional<UsuarioModel> findById(String usuarioId) {
		return usuarioRepository.findById(usuarioId);
	}
}
