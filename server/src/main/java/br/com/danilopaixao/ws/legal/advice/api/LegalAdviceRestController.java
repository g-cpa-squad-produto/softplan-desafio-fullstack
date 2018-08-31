package br.com.danilopaixao.ws.legal.advice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.ws.legal.advice.LegalAdviceRequest;
import br.com.danilopaixao.ws.legal.advice.LegalAdviceResponse;
import br.com.danilopaixao.ws.legal.advice.LegalAdviceService;
import io.swagger.annotations.ApiOperation;

@RestController
public class LegalAdviceRestController {
	
	@Autowired
	private LegalAdviceService service;
	
	@ApiOperation("EndPoint to get LegalAdvice by ID ")
	@GetMapping(value = "/api/v1/legal-advice/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody LegalAdviceResponse getLegalAdvice(
    		@PathVariable(value = "id", required = true) final Long id ) {
		return service.getById(id);
	}	

	@ApiOperation("Endpoint to get ALL LegalAdvicees")
	@GetMapping(value = "/api/v1/legal-advice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody List<LegalAdviceResponse> getAllLegalAdvices() {
		return service.getByAllLegalAdvices();
    }
	
	@ApiOperation("Endpoint to create new LegalAdvice")
	@PostMapping(value = "/api/v1/legal-advice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody LegalAdviceResponse saveLegalAdvice(
    		@RequestBody(required = true) final LegalAdviceRequest process) {
		return service.save(process);
    }
	
	@ApiOperation("Endpoint to update a LegalAdvice")
	@PostMapping(value = "/api/v1/legal-advice/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody LegalAdviceResponse upDateLegalAdvice(
    		@PathVariable(value = "id", required = true) final Long id ,
    		@RequestBody(required = true) final LegalAdviceRequest process) {
		return service.save(id, process);
    }
	

}