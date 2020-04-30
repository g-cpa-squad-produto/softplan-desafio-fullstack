package br.com.softplan.backend.parecer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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
		return mongoTemplate.find(query, ParecerModel.class);
	}

	public ParecerModel findById(String parecerId, String usuarioId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuarioId").is(usuarioId));
		query.addCriteria(Criteria.where("parecerId").is(parecerId));
		return mongoTemplate.findOne(query, ParecerModel.class);
	}
	public List<ParecerModel> findByProcesso(String processoId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("processoId").is(processoId));
		return mongoTemplate.find(query, ParecerModel.class);
	}
}
