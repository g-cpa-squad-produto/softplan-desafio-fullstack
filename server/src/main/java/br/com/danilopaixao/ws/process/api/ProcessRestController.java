package br.com.danilopaixao.ws.process.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.ws.process.ProcessRequest;
import br.com.danilopaixao.ws.process.ProcessResponse;
import br.com.danilopaixao.ws.user.UserResponse;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProcessRestController {
	
	
	@ApiOperation("EndPoint to get Process by ID ")
	@GetMapping(value = "/api/v1/processes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProcessResponse getProcess(
    		@PathVariable(value = "id", required = true) final Long id ) {
		return null;
	}	
	
	@ApiOperation("Endpoint to get ALL Processes")
	@GetMapping(value = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody List<ProcessResponse> getAllUsers() {
		return null;
    }
	
	@ApiOperation("Endpoint to create new Process")
	@PostMapping(value = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProcessResponse saveProcess(
    		@RequestBody(required = true) final ProcessRequest process) {
		return null;
    }
	
	@ApiOperation("Endpoint to update a Process")
	@PostMapping(value = "/api/v1/processes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProcessResponse upDateProcess(
    		@PathVariable(value = "id", required = true) final Long id ,
    		@RequestBody(required = true) final ProcessRequest process) {
		return null;
    }
	
	@ApiOperation("Endpoint to inative a Process")
	@DeleteMapping(value = "/api/v1/processes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserResponse cancelProcess(
    		@PathVariable(value = "id", required = true) final Long id) {
		return null;//this.service.save(user);
    }
	

}

