package com.thiagoag.wsmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiagoag.wsmongo.DTO.LegalProcessDTO;
import com.thiagoag.wsmongo.domain.LegalProcess;
import com.thiagoag.wsmongo.resources.util.URL;
import com.thiagoag.wsmongo.services.LegalProcessService;

@RestController
@RequestMapping(value="/processes")
public class LegalProcessResource {

	@Autowired
	private LegalProcessService service;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<LegalProcess> findById(@PathVariable String id){
		LegalProcess obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/procnumsearch", method=RequestMethod.GET)
	public ResponseEntity<List<LegalProcess>> findByProcNumber(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<LegalProcess> list = service.findByProcessNumber(text);
		return ResponseEntity.ok().body(list);		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	public ResponseEntity<List<LegalProcess>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<LegalProcess> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);		
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody LegalProcessDTO objDTO){
		LegalProcess obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		//To return a header with the created resource URL is good practice
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody LegalProcessDTO objDTO, @PathVariable String id){
		LegalProcess obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();	
	}
}
