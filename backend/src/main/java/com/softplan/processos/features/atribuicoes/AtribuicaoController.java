package com.softplan.processos.features.atribuicoes;

import com.softplan.processos.common.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/atribuicoes")
public class AtribuicaoController extends AbstractController<Atribuicao, Long> {

    @Override
    @PostMapping
    public ResponseEntity<Atribuicao> save(@RequestBody Atribuicao entity) {
        entity.setDhAtribuicao(LocalDateTime.now());
        return super.save(entity);
    }

}
