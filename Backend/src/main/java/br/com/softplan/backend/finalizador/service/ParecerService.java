package br.com.softplan.backend.finalizador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.com.softplan.backend.finalizador.model.ParecerModel;
import br.com.softplan.backend.finalizador.repository.ParecerRepository;

@Service
public class ParecerService {

	private final ParecerRepository parecerRepository;

	private final MongoTemplate mongoTemplate;

	public ParecerService(ParecerRepository parecerRepository, MongoTemplate mongoTemplate) {
		this.parecerRepository = parecerRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public ParecerModel saveParecer(ParecerModel parecerModel){
		return parecerRepository.save(parecerModel);
	}

	public ParecerModel updateParecer(ParecerModel parecerModel){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(parecerModel.getParecerId()));
		Update parecer = new Update();
		parecer.set("resposta", parecerModel.getResposta());
		return mongoTemplate.findAndModify(query, parecer, ParecerModel.class);
	}

	public List<ParecerModel> findAll(String usuarioId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuarioId").is(usuarioId));
		query.addCriteria(Criteria.where("descricao").exists(false));
		return mongoTemplate.find(query, ParecerModel.class);
	}

	public Optional<ParecerModel> findById(String parecerId) {
		return parecerRepository.findById(parecerId);
	}
}
