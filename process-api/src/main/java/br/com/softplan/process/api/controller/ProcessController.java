package br.com.softplan.process.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.softplan.process.api.dto.Summary;
import br.com.softplan.process.api.entity.Process;
import br.com.softplan.process.api.entity.ProcessReview;
import br.com.softplan.process.api.entity.User;
import br.com.softplan.process.api.enums.ProfileEnum;
import br.com.softplan.process.api.enums.StatusEnum;
import br.com.softplan.process.api.response.Response;
import br.com.softplan.process.api.security.jwt.JwtTokenUtil;
import br.com.softplan.process.api.service.ProcessService;
import br.com.softplan.process.api.service.UserService;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*")
public class ProcessController {

	@Autowired
	private ProcessService processService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	private void validateCreateProcess(Process process, BindingResult result) {
		if (process.getSubject() == null) {
			result.addError(new ObjectError("Process", "Assunto não informado"));
			return;
		}	
	}

	public User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String email = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByEmail(email);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER')")
	public ResponseEntity<Response<Process>> createOrUpdate(HttpServletRequest request, @RequestBody Process process,
			BindingResult result) {

		Response<Process> response = new Response<Process>();

		try {
			validateCreateProcess(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			process.setStatus(StatusEnum.Pending);
			process.setUser(userFromRequest(request));
			process.setDate(new Date());
			process.setNumber(generateNumber());
			//process.getAssignedUser()
			
			System.out.println(process);
			
			Process processPersisted = (Process) processService.createOrUpdate(process);
			response.setData(processPersisted);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private Integer generateNumber() {
		return new Random().nextInt(9999);
	}

	private void validateUpdateProcess(Process process, BindingResult result) {
		if (process.getId() == null) {
			result.addError(new ObjectError("Process", "Id não informado [UPDATE PROCESS]"));
			return;
		}

		if (process.getSubject() == null) {
			result.addError(new ObjectError("Process", "Assunto não informado"));
			return;
		}
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER')")
	public ResponseEntity<Response<Process>> update(HttpServletRequest request, @RequestBody Process process,
			BindingResult result) {

		Response<Process> response = new Response<Process>();

		try {
			validateUpdateProcess(process, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Process processCurrent = processService.findById(process.getId());

			process.setStatus(processCurrent.getStatus());
			process.setUser(processCurrent.getUser());
			process.setDate(processCurrent.getDate());
			process.setNumber(processCurrent.getNumber());

			if (processCurrent.getAssignedUser() != null) {
				process.setAssignedUser(processCurrent.getAssignedUser());
			}

			Process processPersisted = (Process) processService.createOrUpdate(process);
			response.setData(processPersisted);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER', 'ROLE_PROCESS_END')")
	public ResponseEntity<Response<Process>> findById(@PathVariable("id") String id) {

		Response<Process> response = new Response<Process>();
		Process process = processService.findById(id);

		if (process == null) {
			response.getErrors().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		List<ProcessReview> reviews = new ArrayList<ProcessReview>();
		Iterable<ProcessReview> reviewCurrent = processService.listProcessReview(process.getId());

		for (Iterator<ProcessReview> iterator = reviewCurrent.iterator(); iterator.hasNext();) {
			ProcessReview review = (ProcessReview) iterator.next();
			review.setProcess(null);
			reviews.add(review);
		}

		process.setReviews(reviews);

		response.setData(process);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {

		Response<String> response = new Response<String>();
		Process process = processService.findById(id);

		if (process == null) {
			response.getErrors().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		processService.delete(id);

		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER', 'ROLE_PROCESS_END')")
	public ResponseEntity<Response<Page<Process>>> findAll(HttpServletRequest request, @PathVariable("page") int page,
			@PathVariable("count") int count) {

		Response<Page<Process>> response = new Response<Page<Process>>();
		Page<Process> processes = null;
		User userRequest = userFromRequest(request);

		if (userRequest.getProfile().equals(ProfileEnum.ROLE_PROCESS_STARTER)) {
			processes = processService.findByCurrentUser(page, count, userRequest.getId());
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_PROCESS_END)) {
			processes = processService.listProcess(page, count);
		}

		response.setData(processes);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{page}/{count}/{number}/{subject}/{status}/{priority}/{assigned}")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER', 'ROLE_PROCESS_END')")
	public ResponseEntity<Response<Page<Process>>> findByParams(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count, @PathVariable("number") Integer number,
			@PathVariable("subject") String subject, @PathVariable("status") String status,
			@PathVariable("priority") String priority, @PathVariable("assigned") boolean assigned) {

		subject = subject.equals("uninformed") ? "" : subject;
		status = status.equals("uninformed") ? "" : status;
		priority = priority.equals("uninformed") ? "" : priority;

		Response<Page<Process>> response = new Response<Page<Process>>();
		Page<Process> processes = null;

		if (number > 0) {
			processes = processService.findByNumber(page, count, number);
		} else {
			User userRequest = userFromRequest(request);
			System.out.println(userRequest.getId());

			if (userRequest.getProfile().equals(ProfileEnum.ROLE_PROCESS_STARTER)) {
				processes = processService.findByParametersAndCurrentUser(page, count, subject, status, priority,
						userRequest.getId());
			} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_PROCESS_END)) {
				if (assigned) {
					processes = processService.findByParameterAndAssignedUser(page, count, subject, status, priority,
							userRequest.getId());
				} else {
					processes = processService.findByParameters(page, count, subject, status, priority);
				}
			}
		}

		response.setData(processes);
		return ResponseEntity.ok(response);
	}

	private void validateSubmitReview(String id, String description, BindingResult result) {
		if (id == null || id.equals("")) {
			result.addError(new ObjectError("Process", "Id não informado"));
			return;
		}

		if (description == null || description.equals("")) {
			result.addError(new ObjectError("Process", "Análise não informada"));
			return;
		}
	}

	@PutMapping(value="{id}/{description}")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER', 'ROLE_PROCESS_END')")
	public ResponseEntity<Response<Process>> submitReview(HttpServletRequest request, @PathVariable("id") String id,
			@PathVariable("description") String description, @RequestBody Process process, BindingResult result) {

		Response<Process> response = new Response<Process>();

		try {
			validateSubmitReview(id, description, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Process processCurrent = processService.findById(id);
			processCurrent.setStatus(StatusEnum.Closed);
			Process processPersisted = (Process) processService.createOrUpdate(processCurrent);

			ProcessReview review = new ProcessReview();
			review.setProcess(processPersisted);
			review.setUser(userFromRequest(request));
			review.setDateReview(new Date());
			review.setDescription(description);

			processService.createProcessReview(review);

			response.setData(processPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Response<Summary>> findSummary() {
		Response<Summary> response = new Response<Summary>();
		Summary summary = new Summary();

		int amountPending = 0;
		int amountAnalyzing = 0;
		int amountCanceled = 0;
		int amountClosed = 0;

		Iterable<Process> processes = processService.findAll();

		if (processes != null) {
			for (Iterator<Process> iterator = processes.iterator(); iterator.hasNext();) {
				Process process = (Process) iterator.next();

				if (process.getStatus().equals(StatusEnum.Pending)) {
					amountPending++;
				} else if (process.getStatus().equals(StatusEnum.Analyzing)) {
					amountAnalyzing++;
				} else if (process.getStatus().equals(StatusEnum.Canceled)) {
					amountCanceled++;
				} else if (process.getStatus().equals(StatusEnum.Closed)) {
					amountClosed++;
				}
			}
		}

		summary.setAmountPending(amountPending);
		summary.setAmountAnalyzing(amountAnalyzing);
		summary.setAmountCanceled(amountCanceled);
		summary.setAmountClosed(amountClosed);

		response.setData(summary);

		return ResponseEntity.ok(response);
	}

}
