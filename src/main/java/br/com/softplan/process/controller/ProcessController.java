package br.com.softplan.process.controller;

import br.com.softplan.process.converter.ProcessRequestConverter;
import br.com.softplan.process.converter.ProcessResponseConverter;
import br.com.softplan.process.exception.ApplicationException;
import br.com.softplan.process.request.ProcessRequest;
import br.com.softplan.process.response.ProcessResponse;
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
    private ProcessResponseConverter responseConverter;
    private ProcessRequestConverter requestConverter;

    @Autowired
    public ProcessController(ProcessService service, ProcessResponseConverter responseConverter, ProcessRequestConverter requestConverter) {
        this.service = service;
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid ProcessRequest request) {
        this.service.save(requestConverter.encode(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid ProcessRequest process) {
        process.setId(id);
        this.service.save(requestConverter.encode(process));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessResponse> list() {
        return this.responseConverter.decode(this.service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessResponse find(@PathVariable Long id) throws ApplicationException {
        return this.responseConverter.decode(this.service.findById(id));
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessResponse> findByUser() {
        return this.responseConverter.decode(this.service.findByUserLogged());
    }
}
