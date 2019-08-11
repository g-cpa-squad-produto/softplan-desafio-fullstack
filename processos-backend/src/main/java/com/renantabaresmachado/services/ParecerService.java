package com.renantabaresmachado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.Parecer;
import com.renantabaresmachado.repositories.ParecerRepository;

@Service
public class ParecerService {

	@Autowired
	private ParecerRepository parecerRepository;
	
	public Parecer inserir(Parecer parecer) {
		parecer.setId(null);
		parecer = parecerRepository.save(parecer);
		return parecer;
	}

}
