package br.com.luizgustavo.processevaluation.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgustavo.processevaluation.model.dto.ProcessDto;
import br.com.luizgustavo.processevaluation.model.form.ProcessForm;
import br.com.luizgustavo.processevaluation.service.ProcessService;

@RestController
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private ProcessService processService;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_SCREENING')")
	public ResponseEntity<ProcessDto> insert(@Valid @RequestBody ProcessForm form, HttpServletResponse response) {
		ProcessDto process = this.processService.insert(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(process.getIdProcess()).toUri();
		response.addHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(process);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_SCREENING')")
	public ResponseEntity<Page<ProcessDto>> findAll(Pageable pageable) {
		Page<ProcessDto> processPage = this.processService.findAll(pageable);
		return ResponseEntity.ok(processPage);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_SCREENING')")
	public ResponseEntity<ProcessDto> findById(@PathVariable("id") Long idProcess) {
		ProcessDto process = this.processService.findById(idProcess);
		return ResponseEntity.ok(process);
	}
	
	@GetMapping("/myPendings")
	@PreAuthorize("hasRole('ROLE_CLOSER')")
	public ResponseEntity<List<ProcessDto>> findMyPendings() {
		List<ProcessDto> pendings = this.processService.findMyPendings();
		return ResponseEntity.ok(pendings);
	}
		
}
