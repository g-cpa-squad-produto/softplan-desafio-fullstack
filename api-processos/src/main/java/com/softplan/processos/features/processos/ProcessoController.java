package com.softplan.processos.features.processos;

import com.softplan.processos.common.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/processos")
public class ProcessoController extends AbstractController<Processo, Long> {

}
