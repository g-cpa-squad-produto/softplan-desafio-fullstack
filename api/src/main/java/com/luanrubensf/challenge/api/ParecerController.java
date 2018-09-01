package com.luanrubensf.challenge.api;

import com.luanrubensf.challenge.model.Parecer;
import com.luanrubensf.challenge.model.User;
import com.luanrubensf.challenge.repository.ParecerRepository;
import com.luanrubensf.challenge.repository.UserRepository;
import com.luanrubensf.challenge.representation.ParecerDto;
import com.luanrubensf.challenge.service.ParecerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pareceres")
@Transactional
public class ParecerController {

    @Autowired
    private ParecerDto.Representation representation;

    @Autowired
    private ParecerRepository parecerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParecerService parecerService;

    @GetMapping
    @PreAuthorize("hasRole('FINALIZADOR')")
    public List<ParecerDto> getMeusPareceres(@RequestParam("user") Long userId) {
        User user = userRepository.findUserById(userId);
        List<Parecer> pareceres = parecerRepository.findByUser(user);
        return representation.toRepresentation(pareceres);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('FINALIZADOR')")
    public ParecerDto insert(@PathVariable("id") Long id, @RequestBody ParecerDto dto) {
        Parecer parecerById = parecerRepository.findParecerById(id);
        Parecer parecer = representation.fromRepresentation(dto, Parecer.Builder.from(parecerById));
        return representation.toRepresentation(parecerService.save(parecer));
    }

    @PostMapping
    @PreAuthorize("hasRole('TRIADOR')")
    public void insert(@RequestBody List<ParecerDto> dtos) {
        List<Parecer> pareceres = representation.fromRepresentation(dtos);
        pareceres.forEach(parecer -> parecerService.save(parecer));
    }
}
