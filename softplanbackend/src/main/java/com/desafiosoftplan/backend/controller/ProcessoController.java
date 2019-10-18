package com.desafiosoftplan.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.backend.enums.Status;
import com.desafiosoftplan.backend.model.Processo;
import com.desafiosoftplan.backend.model.dto.ProcessoDTO;
import com.desafiosoftplan.backend.model.dto.ProcessoRespostaDTO;
import com.desafiosoftplan.backend.service.ProcessoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/processo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProcessoController{

	@Autowired
	ProcessoService processoService;
	
	@RequestMapping(method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON})
	public ResponseEntity<List<Processo>> list() {
		return new ResponseEntity<List<Processo>>(processoService.list(),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,consumes= {MediaType.APPLICATION_JSON})
	public ResponseEntity<Processo> updateSemID(@RequestBody @Valid Processo entity) {
		return new ResponseEntity<Processo>(processoService.update(entity),HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes= {MediaType.APPLICATION_JSON})
	public ResponseEntity<Processo> update(@PathVariable(value = "id") long id, @RequestBody @Valid Processo entity) {
		return new ResponseEntity<Processo>(processoService.update(entity),HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") long id) {
		processoService.delete(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON})
	public ResponseEntity<Processo> get(@PathVariable(value = "id") long id) {
		return new ResponseEntity<Processo>(processoService.get(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes= {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<ProcessoRespostaDTO> create(@RequestBody @Valid ProcessoDTO entity) {
		Processo processo = entity.toObj();
		processo = processoService.createNovoProcesso(processo);
		return new ResponseEntity<>(ProcessoRespostaDTO.toDTO(processo),HttpStatus.OK);
	}
	
	@RequestMapping(value="{status}" ,method = RequestMethod.PUT,consumes= {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<Processo> atualizaProcesso(@PathVariable(value = "status") Status status, @RequestBody @Valid Processo entity) {
		Processo retorno = new Processo();
		switch (status) {
		case APROVADO:
			retorno = aprovaProcesso(entity);
			break;
		case REPROVADO:
			retorno = reprovaProcesso(entity);
			break;
		case PENDENTE_AGUARDANDO_AVALIACAO:
			retorno = enviaProcessoParaAvaliacao(entity);
			break;
		case PENDENTE_AGUARDANDO_REVISAO:
			retorno = enviaProcessoParaRevisao(entity);
			break;
		case PENDENTE_READEQUACAO:
			retorno = enviaProcessoParaReadequacao(entity);
			break;
		default: break;
	}
//		switch (status.getId().intValue()) {
//			case 1:
//				retorno = aprovaProcesso(entity);
//				break;
//			case 2:
//				retorno = reprovaProcesso(entity);
//				break;
//			case 3:
//				retorno = enviaProcessoParaAvaliacao(entity);
//				break;
//			case 4:
//				retorno = enviaProcessoParaRevisao(entity);
//				break;
//			case 5:
//				retorno = enviaProcessoParaReadequacao(entity);
//				break;
//			default: break;
//		}
		return new ResponseEntity<>(retorno,HttpStatus.OK);
	}
	
	@RequestMapping(value="/status/{status}" ,method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<List<Processo>> getProcessos(@PathVariable(value = "status")  Status status) {
		List<Processo> retorno = new ArrayList<>();
//		switch (status.getId().intValue()) {
//		case 1:
//			retorno = getProcessosAprovados();
//			break;
//		case 2:
//			retorno = getProcessosReprovados();
//			break;
//		case 3:
//			retorno = getProcessosAguardandoAvaliacao();
//			break;
//		case 4:
//			retorno = getProcessosAguardandoRevisao();
//			break;
//		case 5:
//			retorno = getProcessosAguardandoReadequacao();
//			break;
//		default: break;
//		}
		switch (status) {
		case APROVADO:
			retorno = getProcessosAprovados();
			break;
		case REPROVADO:
			retorno = getProcessosReprovados();
			break;
		case PENDENTE_AGUARDANDO_AVALIACAO:
			retorno = getProcessosAguardandoAvaliacao();
			break;
		case PENDENTE_AGUARDANDO_REVISAO:
			retorno = getProcessosAguardandoRevisao();
			break;
		case PENDENTE_READEQUACAO:
			retorno = getProcessosAguardandoReadequacao();
			break;
		default: break;
		}
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}
	
	private Processo aprovaProcesso(@Valid Processo entity) {
		return processoService.aprovaProcesso(entity);
	}
	
	private Processo reprovaProcesso(@Valid Processo entity) {
		return processoService.reprovaProcesso(entity);
	}
	
	private Processo enviaProcessoParaAvaliacao(@Valid Processo entity) {
		return processoService.enviaProcessoParaAvaliacao(entity);
	}
	
	private Processo enviaProcessoParaReadequacao(@Valid Processo entity) {
		return processoService.enviaProcessoParaReadequacao(entity);
	}
	
	private Processo enviaProcessoParaRevisao(@Valid Processo entity) {
		return processoService.enviaProcessoParaRevisao(entity);
	}

	private List<Processo> getProcessosAprovados(){
		return processoService.getProcessosAprovados();
	}
	
	private List<Processo> getProcessosReprovados(){
		return processoService.getProcessosReprovados();
	}
	
	private List<Processo> getProcessosAguardandoRevisao(){
		return processoService.getProcessosAguardandoRevisao();
	}
	
	private List<Processo> getProcessosAguardandoAvaliacao(){
		return processoService.getProcessosAguardandoAvaliacao();
	}
	
	private List<Processo> getProcessosAguardandoReadequacao(){
		return processoService.getProcessosAguardandoReadequacao();
	}
}
