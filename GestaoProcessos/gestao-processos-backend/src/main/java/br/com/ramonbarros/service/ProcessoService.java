package br.com.ramonbarros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ramonbarros.entity.Processo;
import br.com.ramonbarros.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	public Processo findById(Long id) {
		Optional<Processo> result = this.processoRepository.findById(id);
		
		if(result.isPresent()) {
			Processo processo = result.get();
			return processo;
		} else {
			return null;			
		}
	}

	public List<Processo> listar() {
		return processoRepository.findAll();
	}

	public Processo salvar(Processo processo) {
		return processoRepository.save(processo);
	}
	
	public Processo alterar(Processo processo) {
		Processo novoProcesso = findById(processo.getId());
		updateData(novoProcesso, processo);
		return processoRepository.save(novoProcesso);
		
	}
	
	private void updateData(Processo novoProcesso, Processo processo) {
		novoProcesso.getListaParecer().addAll(processo.getListaParecer());
		novoProcesso.setStatus(processo.getStatus());
	}
	
}
