package com.desafiosoftplan.backend.service;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiosoftplan.backend.enums.Status;
import com.desafiosoftplan.backend.model.Processo;
import com.desafiosoftplan.backend.repository.ProcessoRepository;
@Service
public class ProcessoService {

	@Autowired
	SituacaoService situacaoService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	ProcessoRepository processoRepository;
	
	public Processo createNovoProcesso(@Valid Processo entity) {
		if(entity.getStatus()!=null) {
			return enviaProcessoParaAvaliacao(entity);
		}
//		entity.setStatus(situacaoService.getAguardandoAvaliacao());
		entity.setStatus(statusService.getAguardandoAvaliacao());
		return create(entity);
	}
	
	public Processo aprovaProcesso(@Valid Processo entity) {
//		Situacao situacao = situacaoService.getAprovado();
//		entity.setStatus(situacao);
		entity.setStatus(statusService.getAprovado());
		return update(entity);
	}
	
	public Processo reprovaProcesso(@Valid Processo entity) {
//		entity.setStatus(situacaoService.getReprovado());
		entity.setStatus(statusService.getReprovado());
		return update(entity);
	}
	
	public Processo enviaProcessoParaAvaliacao(@Valid Processo entity) {
//		entity.setStatus(situacaoService.getAguardandoAvaliacao());
		entity.setStatus(statusService.getAguardandoAvaliacao());
		return update(entity);
	}
	
	public Processo enviaProcessoParaReadequacao(@Valid Processo entity) {
//		entity.setStatus(situacaoService.getAguardandoReadequacao());
		entity.setStatus(statusService.getAguardandoReadequacao());
		return update(entity);
	}
	
	public Processo enviaProcessoParaRevisao(@Valid Processo entity) {
//		entity.setStatus(situacaoService.getAguardandoRevisao());
		entity.setStatus(statusService.getAguardandoRevisao());
		return update(entity);
	}
	
	public List<Processo> getProcessosAprovados(){
		return processoRepository.getProcessosPorSituacao(Status.APROVADO); 
	}
	
	public List<Processo> getProcessosReprovados(){
		return processoRepository.getProcessosPorSituacao(Status.REPROVADO); 
	}
	
	public List<Processo> getProcessosAguardandoAvaliacao(){
		return processoRepository.getProcessosPorSituacao(Status.PENDENTE_AGUARDANDO_AVALIACAO); 
	}

	public List<Processo> getProcessosAguardandoRevisao(){
		return processoRepository.getProcessosPorSituacao(Status.PENDENTE_AGUARDANDO_REVISAO); 
	}
	
	public List<Processo> getProcessosAguardandoReadequacao(){
		return processoRepository.getProcessosPorSituacao(Status.PENDENTE_READEQUACAO); 
	}
	
//	public List<Processo> getProcessosAprovados(){
//		return processoRepository.getProcessosComSituacao(1L); 
//	}
//	
//	public List<Processo> getProcessosReprovados(){
//		return processoRepository.getProcessosComSituacao(2L); 
//	}
//	
//	public List<Processo> getProcessosAguardandoAvaliacao(){
//		return processoRepository.getProcessosComSituacao(3L); 
//	}
//
//	public List<Processo> getProcessosAguardandoRevisao(){
//		return processoRepository.getProcessosComSituacao(4L); 
//	}
//	
//	public List<Processo> getProcessosAguardandoReadequacao(){
//		return processoRepository.getProcessosComSituacao(5L); 
//	}
	
	public List<Processo> list() {
		return processoRepository.findAll();
	}
	
	public Processo create( Processo entity) {
		return processoRepository.save(entity);
	}
	
	public Processo update(Processo entity) {
		return processoRepository.save(entity);
	}
	
	public void delete(long id) {
		processoRepository.deleteById(id);
	}
	
	public Processo get(long id) {
		return processoRepository.getOne(id);
	}
	
}
