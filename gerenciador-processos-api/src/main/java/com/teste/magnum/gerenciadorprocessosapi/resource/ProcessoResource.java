package com.teste.magnum.gerenciadorprocessosapi.resource;

import com.teste.magnum.gerenciadorprocessosapi.exception.ProcessoException;
import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.NovoUsuarioProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ParecerDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.service.impl.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/processo")
public class ProcessoResource {

    private final ProcessoService processoService;

    @Autowired
    public ProcessoResource(ProcessoService processoService) {
        this.processoService = processoService;
    }

    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> findAll() throws NotFoundException {
        return ResponseEntity.ok(processoService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProcessoDTO> save(@RequestBody ProcessoDTO processoDTO) {
        return ResponseEntity.ok(processoService.save(processoDTO));
    }

    @PutMapping(value = "/usuario")
    public ResponseEntity<?> addUsers(@RequestBody NovoUsuarioProcessoDTO novoUsuarioProcessoDTO) throws ProcessoException, UsuarioException {
        processoService.addUsers(novoUsuarioProcessoDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/parecer/{numero}")
    public ResponseEntity<ProcessoDTO> addParecer(@PathVariable("numero") Long numero, @RequestBody ParecerDTO parecerDTO) throws ProcessoException {
        return ResponseEntity.ok(processoService.addParecer(numero, parecerDTO));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProcessoDTO> getProcesso(@PathVariable("id") Long id) throws NotFoundException, ProcessoException {
        return ResponseEntity.ok(processoService.getProcesso(id));
    }


}
