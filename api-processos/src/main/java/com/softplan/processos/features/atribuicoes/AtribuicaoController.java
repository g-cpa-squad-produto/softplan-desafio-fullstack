package com.softplan.processos.features.atribuicoes;

import com.softplan.processos.common.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/atribuicoes")
public class AtribuicaoController extends AbstractController<Atribuicao, Long> {
}
