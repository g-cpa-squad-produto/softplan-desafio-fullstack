package br.com.evaluation.process.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evaluation.process.manager.entity.JudicialProcess;
import br.com.evaluation.process.manager.repository.JudicialProcessRepository;
import br.com.evaluation.process.manager.service.JudicialProcessService;

@Service
public class JudicialProcessServiceImpl implements JudicialProcessService {

	@Autowired
	private JudicialProcessRepository processRepository;
	
	@Override
	public JudicialProcess save(JudicialProcess p) {
		return processRepository.save(p);
	}

	@Override
	public Iterable<JudicialProcess> findAll() {
		return processRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		processRepository.deleteById(id);
	}

}
