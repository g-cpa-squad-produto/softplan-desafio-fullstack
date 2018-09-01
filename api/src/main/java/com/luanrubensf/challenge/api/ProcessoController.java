package com.luanrubensf.challenge.api;

import com.luanrubensf.challenge.model.Processo;
import com.luanrubensf.challenge.model.User;
import com.luanrubensf.challenge.repository.ProcessoRepository;
import com.luanrubensf.challenge.repository.UserRepository;
import com.luanrubensf.challenge.representation.ProcessoDto;
import com.luanrubensf.challenge.representation.UserDto;
import com.luanrubensf.challenge.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
@Transactional
public class ProcessoController {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ProcessoService service;

    @Autowired
    private ProcessoDto.Representation representation;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDto.Representation userRepresentation;

    @GetMapping
    @PreAuthorize("hasRole('TRIADOR')")
    public List<ProcessoDto> getAll() {
        List<Processo> processos = processoRepository.findAllBy();
        return representation.toRepresentation(processos);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('TRIADOR')")
    public ProcessoDto getById(@PathVariable("id") Long id) {
        Processo processo = processoRepository.findProcessoById(id);
        return representation.toRepresentation(processo);
    }

    @GetMapping("/{id}/vinculaveis")
    @PreAuthorize("hasRole('TRIADOR')")
    public List<UserDto> vinculaveis(@PathVariable("id") Long id) {
        List<User> vinculaveisQuery = userRepository.findVinculaveisQuery(id);
        return userRepresentation.toRepresentation(vinculaveisQuery);
    }

    @PostMapping
    @PreAuthorize("hasRole('TRIADOR')")
    public ProcessoDto insert(@RequestBody ProcessoDto dto) {
        Processo processo = representation.fromRepresentation(dto);
        Processo saved = service.save(processo);
        return representation.toRepresentation(saved);
    }
}
