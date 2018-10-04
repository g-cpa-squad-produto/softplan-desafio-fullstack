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
import br.com.softplan.api.entity.ProcessFeedback;
import br.com.softplan.api.entity.User;
import br.com.softplan.api.response.Response;
import br.com.softplan.api.service.ProcessFeedbackService;;


/**
 * Controller responsável pelo gerenciamento de de pareceres dos processos
 * @author Marco
 *
 */
@RestController
@RequestMapping("/api/process-feedback")
@CrossOrigin(origins = "*")
public class ProcessFeedBackController {
	
	@Autowired
	private ProcessFeedbackService processService;

	@PostMapping()
	@PreAuthorize("hasAnyRole('TRIADOR')")
	public ResponseEntity<Response<ProcessFeedback>> create(HttpServletRequest request, @RequestBody ProcessFeedback process,
			BindingResult result) {
		Response<ProcessFeedback> response = new Response<ProcessFeedback>();
		try {
			validateCreateProcessFeedback(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			process.setFeedback(null);
			ProcessFeedback processPersisted = (ProcessFeedback) processService.createOrUpdate(process);
			response.setData(processPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Process Feedback already registered !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateProcessFeedback(ProcessFeedback process, BindingResult result) {
		if (process.getProcess() == null || process.getProcess().getId() == null) {
			result.addError(new ObjectError("ProcessFeedback", "Process no information"));
			return;
		}
		if (process.getFinalizator() == null || process.getFinalizator().getId() == null) {
			result.addError(new ObjectError("ProcessFeedback", "Finalizator no information"));
			return;
		}
		if(process.getId() == null) {
			/*Evitando adicionar duas vezes o mesmo finalizador*/
			ProcessFeedback processFeedback = processService.findByProcessAndFinalizator(process.getProcess(), process.getFinalizator());
			if(processFeedback != null) {
				result.addError(new ObjectError("ProcessFeedback", "Finalizator for process already exists"));
				return;
			}
		}
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('TRIADOR', 'FINALIZADOR')")
	public ResponseEntity<Response<ProcessFeedback>> update(HttpServletRequest request, @RequestBody ProcessFeedback process,
			BindingResult result) {
		Response<ProcessFeedback> response = new Response<ProcessFeedback>();
		try {
			validateUpdate(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ProcessFeedback processPersisted = (ProcessFeedback) processService.createOrUpdate(process);
			response.setData(processPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(ProcessFeedback process, BindingResult result) {
		if (process.getId() == null) {
			result.addError(new ObjectError("ProcessFeedback", "Id no information"));
			return;
		}
		validateCreateProcessFeedback(process, result);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('TRIADOR', 'FINALIZADOR')")
	public ResponseEntity<Response<ProcessFeedback>> findById(@PathVariable("id") long id) {
		Response<ProcessFeedback> response = new Response<ProcessFeedback>();
		ProcessFeedback process = processService.findById(id);
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
		ProcessFeedback process = processService.findById(id);
		if (process == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		processService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
    public  ResponseEntity<Response<Page<ProcessFeedback>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<ProcessFeedback>> response = new Response<Page<ProcessFeedback>>();
		Page<ProcessFeedback> processs = processService.findAll(page, count);
		response.setData(processs);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value = "{page}/{count}/{idProcess}")
	@PreAuthorize("hasAnyRole('TRIADOR')")
    public  ResponseEntity<Response<Page<ProcessFeedback>>> findByProcess(HttpServletRequest request, 
    		 							@PathVariable int page, 
    		 							@PathVariable int count,
    		 							@PathVariable String idProcess) {
		Response<Page<ProcessFeedback>> response = new Response<Page<ProcessFeedback>>();
		Page<ProcessFeedback> processes = null;
		if(idProcess != null && !"".equals(idProcess.trim())) {
			processes = processService.findByProcess(page, count, new Process(new Long(idProcess)));
		}
		response.setData(processes);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value = "{page}/{count}/{idFinalizator}/{pending}")
	@PreAuthorize("hasAnyRole('FINALIZADOR')")
	public  ResponseEntity<Response<Page<ProcessFeedback>>> findByFinalizator(HttpServletRequest request, 
			@PathVariable int page, 
			@PathVariable int count,
			@PathVariable String idFinalizator,
			@PathVariable boolean pending) {
		Response<Page<ProcessFeedback>> response = new Response<Page<ProcessFeedback>>();
		Page<ProcessFeedback> processes = null;
		if(idFinalizator != null && !"".equals(idFinalizator.trim())) {
			processes = processService.findByFinalizator(page, count, new User(new Long(idFinalizator)), pending);
		}
		response.setData(processes);
		return ResponseEntity.ok(response);
	}
	
}
