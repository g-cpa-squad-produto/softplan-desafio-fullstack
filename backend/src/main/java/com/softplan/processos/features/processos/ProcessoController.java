package com.softplan.processos.features.processos;

import com.softplan.processos.common.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/processos")
public class ProcessoController extends AbstractController<Processo, Long> {

    @Override
    @PostMapping
    public ResponseEntity<Processo> save(@RequestBody Processo entity) {
        entity.setDhAbertura(LocalDateTime.now());
        return super.save(entity);
    }

}
