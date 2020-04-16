package com.softplan.backend.resource;

import com.softplan.backend.entity.Process;
import com.softplan.backend.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/process")
public class ProcessResource {

    @Autowired
    private ProcessService processService;

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIADOR, FINALIZADOR')")
    public ResponseEntity<Page<Process>> getAllProcesses(Pageable pageable) {
        return ResponseEntity.ok(processService.findAllProcesses(pageable));
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIADOR')")
    public ResponseEntity<Process> newUser(@Valid @RequestBody Process process) throws Exception {
        return ResponseEntity.ok(this.processService.newProcess(process));
    }

    @PutMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIADOR, FINALIZADOR')")
    public ResponseEntity<Process> updateUser(@RequestBody Process process) throws Exception {
        return ResponseEntity.ok(this.processService.updateProcess(process));
    }

    @DeleteMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIADOR')")
    public ResponseEntity<?> deleteUser(@RequestBody Process process) throws Exception {
        this.processService.deleteProcess(process.getId());
        return ResponseEntity.noContent().header("id", process.getId().toString()).build();
    }

}