package br.com.softplan.process.controller;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Process;
import br.com.softplan.process.request.ProcessRequest;
import br.com.softplan.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/Process")
public class ProcessController {

    private final Converter<ProcessRequest, Process> converter;
    private final ProcessService service;

    @Autowired
    public ProcessController(Converter<ProcessRequest, Process> converter, ProcessService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid ProcessRequest request) {
        this.service.save(this.converter.encode(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody @Valid ProcessRequest request) {
        request.setId(id);
        this.service.save(this.converter.encode(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Process> list() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Process find(@PathVariable Long id) {
        return this.service.findById(id);
    }
}
