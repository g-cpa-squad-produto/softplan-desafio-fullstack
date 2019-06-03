package com.softplan.processos.features.pareceres;

import com.softplan.processos.common.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/pareceres")
public class ParecerController extends AbstractController<Parecer, Long> {

    @Override
    @PostMapping
    public ResponseEntity<Parecer> save(@RequestBody Parecer entity) {
        entity.setDhParecer(LocalDateTime.now());
        return super.save(entity);
    }

}
