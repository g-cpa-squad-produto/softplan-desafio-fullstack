package com.challenge.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.api.documents.Proccess;
import com.challenge.api.repositories.ProccessRepository;
import com.challenge.api.services.ProccessService;

@Service
public class ProccessServiceImpl implements ProccessService {
	
	@Autowired
	private ProccessRepository proccessRepository;

	@Override
	public List<Proccess> listAll() {
		return this.proccessRepository.findAll();
	}

	@Override
	public Proccess listByID(String id) {
		return this.proccessRepository.findById(id).orElse(null);
	}

	@Override
	public Proccess create(Proccess proccess) {
		return this.proccessRepository.save(proccess);
	}

	@Override
	public Proccess update(Proccess proccess) {
		return this.proccessRepository.save(proccess);
	}

	@Override
	public void remove(String id) {
		this.proccessRepository.deleteById(id);
	}

}
