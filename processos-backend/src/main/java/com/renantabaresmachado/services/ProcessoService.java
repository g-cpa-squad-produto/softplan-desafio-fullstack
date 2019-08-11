package com.renantabaresmachado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.Processo;
import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.repositories.ProcessoRepository;
import com.renantabaresmachado.services.exeptions.ObjectNotFoundException;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	public Processo inserir(Processo processo) {
		processo.setId(null);
		processo = processoRepository.save(processo);
		return processo;
	}

	public List<Processo> buscarTodos() {
		return processoRepository.findAll();
	}
	
	public Processo buscar(Integer id) {
		Optional<Processo> processo = processoRepository.findById(id);
		return processo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Processo.class.getName()));
	}


}
