package com.example.demo.web.rest;

import com.example.demo.entity.Process;
import com.example.demo.repository.ProcessRepository;
import com.example.demo.security.CurrentUserService;
import com.example.demo.web.rest.errors.BadRequestAlertException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
@Log4j2
public class ProcessResource {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @PostMapping("/processes")
    public ResponseEntity<Process> createProcess(@Valid @RequestBody Process process) throws URISyntaxException {
        log.debug("REST request to save Process : {}", process);
        validate(process);
        setAutor(process);
        process.getReports().forEach( report -> report.setProcess(process));
        Process result = processRepository.save(process);

        return ResponseEntity.created(new URI("/api/processes/" + result.getId()))
                .header("id", result.getId().toString())
                .body(result);
    }

    private void validate(Process process) {

        if (process.getId() != null)
            throw new BadRequestAlertException("Um novo process nao pode ter um ID", "processes", "idExists");

        if(process.getReports().isEmpty())
            throw new BadRequestAlertException("Um processo deve conter pedidos de report", "processes", "reportNull");

    }

    @GetMapping("/processes")
    public ResponseEntity<List<Process>> getAllProcesses() {
        log.debug("REST request to get all Processes");
        return ResponseEntity.ok().body(processRepository.findAll());
    }

    @GetMapping("/processes/author/")
    public ResponseEntity<List<Process>> getAllProcessesByAuthor() {
        log.debug("REST request to get all Processes by author");
        return ResponseEntity.ok().body(processRepository.findAllByAutorId(currentUserService.getCurrentUser().getId()));
    }

    @GetMapping("/processes/{id}")
    public ResponseEntity<Process> getProcess(@PathVariable Long id) {
        log.debug("REST request to get Process : {}", id);
        return processRepository.findById(id)
                .map((process) -> ResponseEntity.ok().body(process))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/processes/user/{id}")
    public ResponseEntity<Process> getProcessByUserId(@PathVariable Long id) {
        log.debug("REST request to get Process : {}", id);
        return processRepository.findById(id)
                .map((process) -> ResponseEntity.ok().body(process))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void setAutor(@RequestBody @Valid Process process) {
        process.setAutor(currentUserService.getCurrentUser());
    }
}
