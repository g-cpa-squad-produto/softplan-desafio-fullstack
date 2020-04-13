package com.ldutra.processos.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldutra.processos.api.dto.AtualizaStatusDTO;
import com.ldutra.processos.api.dto.ProcessoDTO;
import com.ldutra.processos.exception.RegraNegocioException;
import com.ldutra.processos.model.entity.Processo;
import com.ldutra.processos.model.entity.Usuario;
import com.ldutra.processos.model.enums.StatusProcesso;
import com.ldutra.processos.service.ProcessoService;
import com.ldutra.processos.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/processos")
@RequiredArgsConstructor
public class ProcessoResource {

	private final ProcessoService service;
	private final UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value ="parecer" , required = false) String parecer,
			@RequestParam(value = "mes", required = false) Integer mes,
			@RequestParam(value = "ano", required = false) Integer ano,
			@RequestParam("usuario") Long idUsuario
			) {
		
		Processo processoFiltro = new Processo();
		processoFiltro.setParecer(parecer);
		processoFiltro.setMes(mes);
		processoFiltro.setAno(ano);
		
		Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
		if(!usuario.isPresent()) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado para o Id informado.");
		}else {
			processoFiltro.setUsuario(usuario.get());
		}
		
		List<Processo> processos = service.buscar(processoFiltro);
		return ResponseEntity.ok(processos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity obterProcesso( @PathVariable("id") Long id ) {
		return service.obterPorId(id)
					.map( processo -> new ResponseEntity(converter(processo), HttpStatus.OK) )
					.orElseGet( () -> new ResponseEntity(HttpStatus.NOT_FOUND) );
	}

	@PostMapping
	public ResponseEntity salvar( @RequestBody ProcessoDTO dto ) {
		try {
			Processo entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody ProcessoDTO dto ) {
		return service.obterPorId(id).map( entity -> {
			try {
				Processo processo = converter(dto);
				processo.setId(entity.getId());
				service.atualizar(processo);
				return ResponseEntity.ok(processo);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () ->
			new ResponseEntity("Processo não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	@PutMapping("{id}/atualiza-status")
	public ResponseEntity atualizarStatus( @PathVariable("id") Long id , @RequestBody AtualizaStatusDTO dto ) {
		return service.obterPorId(id).map( entity -> {
			StatusProcesso statusSelecionado = StatusProcesso.valueOf(dto.getStatus());
			
			if(statusSelecionado == null) {
				return ResponseEntity.badRequest().body("Não foi possível atualizar o status do lançamento, envie um status válido.");
			}
			
			try {
				entity.setStatus(statusSelecionado);
				service.atualizar(entity);
				return ResponseEntity.ok(entity);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		
		}).orElseGet( () ->
		new ResponseEntity("Processo não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar( @PathVariable("id") Long id ) {
		return service.obterPorId(id).map( entidade -> {
			service.deletar(entidade);
			return new ResponseEntity( HttpStatus.NO_CONTENT );
		}).orElseGet( () -> 
			new ResponseEntity("Processo não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	private ProcessoDTO converter(Processo processo) {
		return ProcessoDTO.builder()
					.id(processo.getId())
					.parecer(processo.getParecer())
					.mes(processo.getMes())
					.ano(processo.getAno())
					.status(processo.getStatus().name())
					.usuario(processo.getUsuario().getId())
					.build();
					
	}
	
	private Processo converter(ProcessoDTO dto) {
		Processo processo = new Processo();
		processo.setId(dto.getId());
		processo.setParecer(dto.getParecer());
		processo.setAno(dto.getAno());
		processo.setMes(dto.getMes());
		
		Usuario usuario = usuarioService
			.obterPorId(dto.getUsuario())
			.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado.") );
		
		processo.setUsuario(usuario);

		
		
		if(dto.getStatus() != null) {
			processo.setStatus(StatusProcesso.valueOf(dto.getStatus()));
		}
		
		return processo;
	}
}
