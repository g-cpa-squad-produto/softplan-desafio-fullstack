package br.com.softplan.services;

import java.util.List;

import br.com.softplan.models.Feedback;
import br.com.softplan.models.Proccess;

public interface IFeedbackService extends IGenericService<Feedback, Long>{
	public List<Feedback> findAllByProcess(Proccess p);

}
