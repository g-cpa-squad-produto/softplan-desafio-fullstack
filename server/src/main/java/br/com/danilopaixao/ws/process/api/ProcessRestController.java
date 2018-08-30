package br.com.danilopaixao.ws.process.api;

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

import br.com.danilopaixao.ws.process.ProcessRequest;
import br.com.danilopaixao.ws.process.ProcessResponse;
import br.com.danilopaixao.ws.process.ProcessService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProcessRestController {
	
	@Autowired
	private ProcessService service;
	
	@ApiOperation("EndPoint to get Process by ID ")
	@GetMapping(value = "/api/v1/processes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProcessResponse getProcess(
    		@PathVariable(value = "id", required = true) final Long id ) {
		return service.getById(id);
	}	
	
	@ApiOperation("Endpoint to get ALL Processes")
	@GetMapping(value = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody List<ProcessResponse> getAllProcesses() {
		return service.getByAllProcesss();
    }
	
	@ApiOperation("Endpoint to create new Process")
	@PostMapping(value = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProcessResponse saveProcess(
    		@RequestBody(required = true) final ProcessRequest process) {
		return service.save(process);
    }
	
	@ApiOperation("Endpoint to update a Process")
	@PostMapping(value = "/api/v1/processes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProcessResponse upDateProcess(
    		@PathVariable(value = "id", required = true) final Long id ,
    		@RequestBody(required = true) final ProcessRequest process) {
		return service.save(id, process);
    }
	

}

