package br.com.evaluation.process.manager.service;

import br.com.evaluation.process.manager.entity.JudicialProcess;

public interface JudicialProcessService {

	public JudicialProcess save(JudicialProcess u);
	
	public Iterable<JudicialProcess> findAll();
	
	public void delete(Long id);
	
	
}
