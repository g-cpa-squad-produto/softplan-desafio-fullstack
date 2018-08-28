package com.thiagoag.wsmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiagoag.wsmongo.DTO.UserDTO;
import com.thiagoag.wsmongo.domain.LegalProcess;
import com.thiagoag.wsmongo.domain.User;
import com.thiagoag.wsmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	
	//@GetMapping also works
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));		
	}
	 
	//@PostMapping also works
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		//To return a header with the created resource URL is good practice
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).build();		
		return ResponseEntity.created(uri).body(new UserDTO(obj));	
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();	
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}/processes", method=RequestMethod.GET)
	public ResponseEntity<List<LegalProcess>> findProcesses(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getProcesses());		
	}
	
}
