package com.softplan.processos.features.pareceres;

import com.softplan.processos.common.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pareceres")
public class ParecerController extends AbstractController<Parecer, Long> {
}
