package br.com.softplan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.entity.Processo;
import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;
	
	public ResponseEntity salvar(Processo processo)
	{
		ResponseEntity responseEntity;
		try {
			processo.setCadastro(new Date());
			processoRepository.save(processo);
			responseEntity = new ResponseEntity(1, "Registro salvo com sucesso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity(4, "Erro ao salvar o registro");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	public Processo findById(String id)
	{
		return processoRepository.findById(id).get();
	}
	
	public ResponseEntity delete(String id) {
		ResponseEntity responseEntity;
		try {
			processoRepository.deleteById(id);
			responseEntity = new ResponseEntity(1, "Registro excluído com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity(4, "Erro ao excluir o Registro");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	public List<Processo> listAll()
	{
		return processoRepository.findAll();
	}
	
	public Processo findByNumero(Integer numero) {
		return processoRepository.findByNumero(numero);
	}
}
