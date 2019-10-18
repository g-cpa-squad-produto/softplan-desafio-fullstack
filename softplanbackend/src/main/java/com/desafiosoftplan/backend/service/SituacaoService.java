package com.desafiosoftplan.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiosoftplan.backend.model.Situacao;
import com.desafiosoftplan.backend.repository.SituacaoRepository;
@Service
public class SituacaoService {

	@Autowired
	SituacaoRepository situacaoRepository;
	
	public Situacao getAprovado() {
		return get(1);
	}

	public Situacao getReprovado() {
		return get(2);
	}
	
	public Situacao getAguardandoAvaliacao() {
		return get(3);
	}
	
	public Situacao getAguardandoRevisao() {
		return get(4);
	}
	
	public Situacao getAguardandoReadequacao() {
		return get(5);
	}	
	
	public List<Situacao> list() {
		return situacaoRepository.findAll();
	}
	
	public Situacao create( Situacao entity) {
		return situacaoRepository.save(entity);
	}
	
	public Situacao update(Situacao entity) {
		return situacaoRepository.save(entity);
	}
	
	public void delete(long id) {
		situacaoRepository.deleteById(id);
	}
	
	public Situacao get(long id) {
		return situacaoRepository.getOne(id);
	}
}
