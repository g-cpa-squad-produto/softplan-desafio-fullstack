package com.challenge.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.api.documents.Proccess;
import com.challenge.api.responses.Response;
import com.challenge.api.services.ProccessService;

@RestController
@RequestMapping(path="/api/proccess")
public class ProccessController {
	
	@Autowired
	private ProccessService  proccessService;
	
	
	@GetMapping
	public ResponseEntity<Response<List<Proccess>>> listAll(){
		return ResponseEntity.ok(new Response<List<Proccess>>(this.proccessService.listAll()));
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Response<Proccess>> listById(@PathVariable(name = "id") String id){
		return ResponseEntity.ok(new Response<Proccess>(this.proccessService.listByID(id)));
	}
	
	@PostMapping
	public ResponseEntity<Response<Proccess>> create(@Valid @RequestBody Proccess proccess, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Proccess>(errors));
		}
		
		return ResponseEntity.ok(new Response<Proccess>(this.proccessService.create(proccess)));		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Response<Proccess>> update(@PathVariable(name="id") String id,@Valid @RequestBody Proccess proccess, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Proccess>(errors));
		}
		proccess.setId(id);
		return ResponseEntity.ok(new Response<Proccess>(this.proccessService.update(proccess)));		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable(name="id") String id){
		this.proccessService.remove(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
}


