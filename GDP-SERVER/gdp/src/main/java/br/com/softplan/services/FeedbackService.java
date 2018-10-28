package br.com.softplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.models.Feedback;
import br.com.softplan.models.Proccess;
import br.com.softplan.repository.IFeedbackRepository;
import br.com.softplan.repository.IProcessRepository;

@Service
public class FeedbackService extends GenericService<Feedback, Long> implements IFeedbackService {
	
	@Autowired
	private IFeedbackRepository repository;
	
	
	@Autowired
	private IProcessRepository processRepository;
	
	@Override
	public List<Feedback> findAllByProcess(Proccess process) {
		
		Proccess aux = this.processRepository.getOne(process.getId());
		
		return this.repository.findAllByProcess(aux);
	}



}
