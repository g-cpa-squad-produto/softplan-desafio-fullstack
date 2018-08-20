package com.processos.luiz.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.processos.luiz.ParecerProcessoDTO;
import com.processos.luiz.exception.ProcessoException;
import com.processos.luiz.models.Processo;
import com.processos.luiz.repository.ProcessoRepository;
import com.processos.luiz.service.ProcessoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="API REST Processos")
@RestController
@RequestMapping("/v1")
public class ProcessoResource {
	@Autowired
	private ProcessoRepository processoRepository;
	@Autowired
	private ProcessoService  processoService;
	
	@ApiOperation(value="Retorna lista de Processos")
	@GetMapping(path="triador/processos", produces="application/json")
	public @ResponseBody  ArrayList<Processo> listaProcessos()throws ProcessoException{
		ArrayList<Processo> processos = (ArrayList<Processo>) processoRepository.findAll();
		return processos;
	}
	@ApiOperation(value="Retorna lista de Processos")
	@GetMapping(path="finalizador/processos/{login}")
	public List<Processo> listaProcessosUsuarios(@PathVariable(value="login") String login) 
			throws ProcessoException{
		return  listarProcessosTriadorFinalizador(login);
	}
	@ApiOperation(value="Retorna lista de Processos")
	@GetMapping(path="triador/processos/{login}")
	public List<Processo> listaProcessos(@PathVariable(value="login") String login) 
			throws ProcessoException{
		return listarProcessosTriadorFinalizador(login);
	}
	private List<Processo> listarProcessosTriadorFinalizador(String login) throws ProcessoException{
		return  processoService.listaProcessosUsuarios(login);
	}
	@ApiOperation(value="Salva um processo")
	@PostMapping(path="triador/processos")
	public Processo salvarProcesso(@RequestBody @Valid Processo processo)throws ProcessoException{
		processoService.salvarProcesso(processo);
		return processo;
	}
	@ApiOperation(value="Salva um parecer no processo")
	@PostMapping(path="finalizador/processos")
	public Processo cadastraParecer(@RequestBody @Valid ParecerProcessoDTO parecerProcessoDTO)throws ProcessoException{
		return processoService.cadastrarParecer(parecerProcessoDTO);
	}
}
