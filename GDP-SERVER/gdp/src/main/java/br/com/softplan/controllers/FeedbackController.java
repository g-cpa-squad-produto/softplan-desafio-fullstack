package br.com.softplan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.models.Feedback;
import br.com.softplan.models.Proccess;
import br.com.softplan.services.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController extends GenericController<Feedback, Long> {

	@Autowired
	private FeedbackService service;
	
	@PostMapping("/by-process")
	public List<Feedback> findAllByProcess(@RequestBody Proccess process) {
		return this.service.findAllByProcess(process);
	}
}
