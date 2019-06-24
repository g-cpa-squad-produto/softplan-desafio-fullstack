package com.challenge.api.services;

import java.util.List;

import com.challenge.api.documents.Proccess;

public interface ProccessService {
	List<Proccess> listAll();
	
	Proccess listByID(String id);
	
	Proccess create(Proccess proccess);
	
	Proccess update(Proccess proccess);
	
	void remove(String id);
}
