package br.com.softplan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.entity.Parecer;
import br.com.softplan.entity.Processo;
import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.repository.ParecerRepository;
import br.com.softplan.repository.ProcessoRepository;

@Service
public class ParecerService {
	
	@Autowired
	private ParecerRepository parecerRepository;
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	private ResponseEntity responseEntity;
	
	public ResponseEntity salvar(Processo processo)
	{
		try {
			processo.getParecer().setCadastro(new Date());
			parecerRepository.save(processo.getParecer());
			processoRepository.save(processo);
			this.responseEntity = new ResponseEntity(1, "Registro incluído com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.responseEntity = new ResponseEntity(4, "Erro ao realizar a operação!");
			e.printStackTrace();
		}
		return this.responseEntity;
	}
	
	public Parecer findById(String id)
	{
		return parecerRepository.findById(id).get();
	}
	
	public String delete(String id) {
		parecerRepository.deleteById(id);
		return "Registro excluído com sucesso";
	}
	
	public List<Parecer> listAll()
	{
		return parecerRepository.findAll();
	}
	
	public List<Processo> listProcessosSemParecer() {
		List<Processo> lista = new ArrayList<>();
		lista = processoRepository
				.findAll()
				.stream()
				.filter(proc -> proc.getParecer() == null)
				.collect(Collectors.toList());
		return lista;
	}

	public Parecer getByProcessoId(String id) {
		// TODO Auto-generated method stub
		return processoRepository.findById(id).get().getParecer();
	}
}
