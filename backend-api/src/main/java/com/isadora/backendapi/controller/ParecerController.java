package com.isadora.backendapi.controller;

import com.isadora.backendapi.domain.Parecer;
import com.isadora.backendapi.domain.Processo;
import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.repositories.ProcessoRepository;
import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.ParecerService;
import com.isadora.backendapi.services.ProcessoService;
import com.isadora.backendapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parecer")
@CrossOrigin(origins = "*")
public class ParecerController {

    @Autowired
    private ParecerService parecerService;

    @Autowired
    private ProcessoService processoService;


    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public Iterable<Parecer> getAllPareceres() {

        return parecerService.findAllPareceres();
    }


    @GetMapping("/{idProcesso}")
    @PreAuthorize("hasAnyRole('TRIADOR', 'FINALIZADOR')")
    public  ResponseEntity<?> findByProcesso(@PathVariable Long idProcesso) {

        Optional<Processo> processo = processoService.findById(idProcesso);
        Parecer parecer = parecerService.findByProcesso(processo.get());
        return new ResponseEntity<Parecer>(parecer, HttpStatus.OK);
    }


    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('TRIADOR', 'FINALIZADOR')")
    public  ResponseEntity<?> findByUsuario(@PathVariable Long idUsuario) {
        Iterable<Parecer> pareceres;
        pareceres = parecerService.findByUsuario(idUsuario);
        return new ResponseEntity<List<Parecer>>((List<Parecer>) pareceres, HttpStatus.OK);
    }


    @PostMapping("{usuarioId}/{processoId}")
    @PreAuthorize("hasAnyRole('FINALIZADOR')")
    public ResponseEntity<?> createParecer(@Valid @RequestBody Parecer parecer, @PathVariable Long usuarioId, @PathVariable Long processoId , BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationServive(result);
        if (errorMap != null) {
            return errorMap;
        }

        Parecer parecerNovo = parecerService.save(parecer, usuarioId, processoId);
        return new ResponseEntity<Parecer>(parecerNovo, HttpStatus.CREATED);
    }


}
