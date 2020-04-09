package fmreina.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fmreina.app.model.Process;
import fmreina.app.persistence.ProcessRepository;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class ProcessController {
	
	@Autowired
	private ProcessRepository processRepository;
	
	@GetMapping(path = "/process")
	public List<Process> getAllProcess(){
		return processRepository.findAll();
	}
	
	@GetMapping("/process/{processId}")
	public Process getProcessById(@PathVariable Long processId){
		Optional<Process> op = processRepository.findById(processId);
		
		if(op.isPresent()){
			return op.get();
		}
		
		return null;
	}
	
	@PostMapping("/process/{processId}")
	public ResponseEntity<Process> createProcess(@RequestBody Process process){
		Process processCreated = processRepository.save(process);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{processId}").buildAndExpand(processCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/process/{processId}")
	public ResponseEntity<Process> updateprocess(@PathVariable Long processId, @RequestBody Process process ){
		Process processUpdated = processRepository.save(process);
		
		return new ResponseEntity<Process>(processUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/process/{processId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long processId) {
		processRepository.deleteById(processId);

		return ResponseEntity.noContent().build();
	}
}
