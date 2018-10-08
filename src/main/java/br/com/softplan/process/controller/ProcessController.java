package br.com.softplan.process.controller;

import br.com.softplan.process.model.Process;
import br.com.softplan.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/process")
public class ProcessController {

    private ProcessService service;

    @Autowired
    public ProcessController(ProcessService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Process process) {
        this.service.save(process);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid Process process) {
        process.setId(id);
        this.service.save(process);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Process> list() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Process find(@PathVariable Long id) throws Exception {
        return this.service.findById(id);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Process> findByUser() {
        return this.service.findByUserLogged();
    }

    @PostMapping("/{processId}/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void relateUserToSight(@PathVariable Long processId,
                                  @PathVariable Long userId) throws Exception {
        this.service.relateUserToSight(processId, userId);
    }
}
