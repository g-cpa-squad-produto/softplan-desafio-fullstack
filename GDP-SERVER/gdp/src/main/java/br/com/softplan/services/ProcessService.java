package br.com.softplan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.exceptions.ProcessNotFound;
import br.com.softplan.models.Proccess;
import br.com.softplan.repository.IProcessRepository;

@Service
public class ProcessService extends GenericService<Proccess, Long> { 
	
@Autowired
private IProcessRepository repository;
	
@Override
public void update(Proccess t) {
	
	Optional<Proccess> aux = this.repository.findById(t.getId());
		
	if (aux == null) {
		throw new ProcessNotFound("Process Not Found");
	}
	
	Proccess p = aux.get();
	
	p.setUsers(null);
	
	this.repository.save(t);
}
	
	
}	
