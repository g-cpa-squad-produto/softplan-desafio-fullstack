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

import com.challenge.api.documents.Comments;
import com.challenge.api.responses.Response;
import com.challenge.api.services.CommentsService;

@RestController
@RequestMapping(path="/api/comments")
public class CommentsController {
	
	@Autowired
	private CommentsService  commentsService;
	
	
	@GetMapping
	public ResponseEntity<Response<List<Comments>>> listAll(){
		return ResponseEntity.ok(new Response<List<Comments>>(this.commentsService.listAll()));
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Response<Comments>> listById(@PathVariable(name = "id") String id){
		return ResponseEntity.ok(new Response<Comments>(this.commentsService.listByID(id)));
	}
	
	@PostMapping
	public ResponseEntity<Response<Comments>> create(@Valid @RequestBody Comments comments, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Comments>(errors));
		}
		
		return ResponseEntity.ok(new Response<Comments>(this.commentsService.create(comments)));		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Response<Comments>> update(@PathVariable(name="id") String id,@Valid @RequestBody Comments comments, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Comments>(errors));
		}
		comments.setId(id);
		return ResponseEntity.ok(new Response<Comments>(this.commentsService.update(comments)));		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable(name="id") String id){
		this.commentsService.remove(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
}


