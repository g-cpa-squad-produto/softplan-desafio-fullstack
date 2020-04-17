package com.softplan.backend.resource;

import com.softplan.backend.entity.Process;
import com.softplan.backend.enumeration.Status;
import com.softplan.backend.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class ProcessResource {

    @Autowired
    private ProcessService processService;

    @GetMapping("/process/")
    @PreAuthorize("hasAnyAuthority('TRIADOR', 'FINALIZADOR')")
    public ResponseEntity<Page<Process>> getAllProcesses(Pageable pageable) {
        return ResponseEntity.ok(processService.findAllProcesses(pageable));
    }

    @GetMapping("/process-assign/")
    @PreAuthorize("hasAnyAuthority('FINALIZADOR')")
    public ResponseEntity<Page<Process>> getAllProcessByCurrentUser(Pageable pageable) {
        return ResponseEntity.ok(processService.findAllProcessByCurrentUser(pageable));
    }

    @GetMapping("/process/{id}")
    @PreAuthorize("hasAnyAuthority('TRIADOR', 'FINALIZADOR')")
    public ResponseEntity<Process> getProcessById(@PathVariable Long id) {
        return ResponseEntity.ok(processService.findProcessById(id));
    }

    @PostMapping(value = "/process/")
    @PreAuthorize("hasAnyAuthority('TRIADOR')")
    public ResponseEntity<Process> newProcess(@Valid @RequestBody Process process) throws Exception {
        return ResponseEntity.ok(this.processService.newProcess(process));
    }

    @PutMapping(value = "/process/")
    @PreAuthorize("hasAnyAuthority('TRIADOR')")
    public ResponseEntity<Process> updateProcess(@RequestBody Process process) throws Exception {
        return ResponseEntity.ok(this.processService.updateProcess(process));
    }

    @DeleteMapping(value = "/process/{id}")
    @PreAuthorize("hasAnyAuthority('TRIADOR')")
    public void deleteProcess(@PathVariable Long id) throws Exception {
        this.processService.deleteProcess(id);
    }

    @PutMapping(value = "/process/{id}/accept")
    @PreAuthorize("hasAnyAuthority('FINALIZADOR')")
    public ResponseEntity<Process> acceptProcessById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(processService.changeStatuProcessById(id, Status.APPROVED));
    }

    @PutMapping(value = "/process/{id}/deny")
    @PreAuthorize("hasAnyAuthority('FINALIZADOR')")
    public ResponseEntity<Process> denyProcessById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(processService.changeStatuProcessById(id, Status.DENIED));
    }

}