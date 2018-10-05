package br.com.softplan.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.response.Response;
import br.com.softplan.api.service.ProcessService;


/**
 * Controller responsável pelo gerenciamento de processos
 * @author Marco
 *
 */
@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*")
public class ProcessController {
	
	@Autowired
	private ProcessService processService;

	@PostMapping()
	@PreAuthorize("hasAnyRole('TRIADOR')")
	public ResponseEntity<Response<Process>> create(HttpServletRequest request, @RequestBody Process process,
			BindingResult result) {
		Response<Process> response = new Response<Process>();
		try {
			validateCreateProcess(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Process processPersisted = (Process) processService.createOrUpdate(process);
			response.setData(processPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Process already registered !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateProcess(Process process, BindingResult result) {
		if (process.getNumber() == null || "".equals(process.getNumber().trim())) {
			result.addError(new ObjectError("Process", "Number no information"));
			return;
		}
		if (process.getDescription() == null || "".equals(process.getDescription().trim())) {
			result.addError(new ObjectError("Process", "Description no information"));
			return;
		}
		
		Process processDB = this.processService.findByNumber(process.getNumber());
		if(processDB != null && !processDB.getId().equals(process.getId())) {
			result.addError(new ObjectError("Process", "Process number already exists"));
			return;
		}
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('TRIADOR')")
	public ResponseEntity<Response<Process>> update(HttpServletRequest request, @RequestBody Process process,
			BindingResult result) {
		Response<Process> response = new Response<Process>();
		try {
			validateUpdate(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Process processPersisted = (Process) processService.createOrUpdate(process);
			response.setData(processPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(Process process, BindingResult result) {
		if (process.getId() == null) {
			result.addError(new ObjectError("Process", "Id no information"));
			return;
		}
		validateCreateProcess(process, result);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
	public ResponseEntity<Response<Process>> findById(@PathVariable("id") long id) {
		Response<Process> response = new Response<Process>();
		Process process = processService.findById(id);
		if (process == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(process);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") long id) {
		Response<String> response = new Response<String>();
		Process process = processService.findById(id);
		if (process == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		processService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
    public  ResponseEntity<Response<Page<Process>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Process>> response = new Response<Page<Process>>();
		Page<Process> processs = processService.findAll(page, count);
		response.setData(processs);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value = "{page}/{count}/{number}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
    public  ResponseEntity<Response<Page<Process>>> findByParams(HttpServletRequest request, 
    		 							@PathVariable int page, 
    		 							@PathVariable int count,
    		 							@PathVariable String number) {
		
		
		Response<Page<Process>> response = new Response<Page<Process>>();
		Page<Process> processes = null;
		if(!"uninformed".equals(number)) {
			processes = processService.findByNumber(page, count, number);
			if(processes == null || processes.getNumberOfElements() == 0)
				response.getErrors().add("Record not found.");
		}else {
			return findAll(page, count);
		}
		response.setData(processes);
		return ResponseEntity.ok(response);
    }
	
}
