package com.isadora.backendapi.controller;

import com.isadora.backendapi.domain.Processo;
import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.ProcessoService;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/processo")
@CrossOrigin(origins = "*")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/{processoId}")
    @PreAuthorize("hasAnyRole('TRIADOR')")
    public ResponseEntity<?> getProcessoById(@PathVariable Long processoId) {
        Optional<Processo> processo = processoService.findById(processoId);
        return new ResponseEntity<Optional<Processo>>(processo, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('TRIADOR')")
    public Iterable<Processo> getAllProcessos() {
        return processoService.findAllProcessos();
    }

    @GetMapping("/pendentes")
    @PreAuthorize("hasAnyRole('FINALIZADOR')")
    public Iterable<Processo> getAllProcessosSemParecer() {
        return processoService.findByParecerNull();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('TRIADOR')")
    public ResponseEntity<?> createNewProcesso(@Valid @RequestBody Processo processo, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationServive(result);
        if (errorMap != null) {
            return errorMap;
        }
        Processo processoNovo = processoService.save(processo);
        return new ResponseEntity<Processo>(processoNovo, HttpStatus.CREATED);
    }


    @DeleteMapping("/{processoId}")
    @PreAuthorize("hasAnyRole('TRIADOR')")
    public ResponseEntity<?> deleteProcesso(@PathVariable Long processoId) {
        processoService.deleteProcessoById(processoId);

        return new ResponseEntity<String>("Processo '" + processoId + "' deletado.", HttpStatus.OK);
    }
}
