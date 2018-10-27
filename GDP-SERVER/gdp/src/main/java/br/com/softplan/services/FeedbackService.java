package br.com.softplan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.models.Feedback;
import br.com.softplan.models.Proccess;
import br.com.softplan.repository.IFeedbackRepository;

@Service
public class FeedbackService extends GenericService<Feedback, Long> implements IFeedbackService {
	
	@Autowired
	private IFeedbackRepository repository;
	
	@Override
	public List<Feedback> findAllByProcess(Proccess process) {
		return this.repository.findAllByProcess(process);
	}



}
