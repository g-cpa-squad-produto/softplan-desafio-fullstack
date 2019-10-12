package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.security.ApiResponse;
import br.com.softplan.processmanagement.services.ProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class ProcessesController {

    @Autowired
    private ProcessesService processesService;

    @GetMapping
    ResponseEntity<List<Process>> list() {
        return ResponseEntity.ok(processesService.list());
    }

    @PostMapping
    ResponseEntity<ApiResponse> save(@RequestBody @Valid Process process) {
        process = processesService.save(process);
        return ResponseEntity.ok(new ApiResponse(true, "Process created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Process> searchById(@PathVariable("id") Long id) {
        Process process = processesService.searchById(id);
        return ResponseEntity.ok(process);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid Process process, @PathVariable("id") Long id) {
        process.setId(id);
        processesService.update(process);
        return ResponseEntity.ok(new ApiResponse(true, "Process updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        processesService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Process removed"));
    }

    @GetMapping(value = "/user/{id}")
    ResponseEntity<List<Process>> listByUser(@PathVariable("id") Long idUser) {
        return ResponseEntity.ok(processesService.listByUser(idUser));
    }

}