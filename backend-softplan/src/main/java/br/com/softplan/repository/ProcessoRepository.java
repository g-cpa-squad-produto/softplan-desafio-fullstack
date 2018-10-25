package br.com.softplan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.entity.Processo;

public interface ProcessoRepository extends MongoRepository<Processo, String> {
	
	public Processo findByNumero(Integer numero);

}
