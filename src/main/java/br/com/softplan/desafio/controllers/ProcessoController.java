package br.com.softplan.desafio.controllers;

import br.com.softplan.desafio.bo.ProcessoBO;
import br.com.softplan.desafio.models.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/processo")
public class ProcessoController {

    @Autowired
    private ProcessoBO processoBO;

    @GetMapping
    public List<Processo> search() {
        return processoBO.findAllPendentes();
    }

    @PostMapping("/{usuarioCodigo}")
    public Processo salva(@Valid @RequestBody Processo processo, @PathVariable("usuarioCodigo") Long usuarioCodigo) {
        return processoBO.salva(processo, usuarioCodigo);
    }

    @PutMapping("/{usuarioCodigo}")
    public Processo atualiza(@Valid @RequestBody Processo processo, @PathVariable(value = "usuarioCodigo") Long usuarioCodigo) {
        return processoBO.update(processo, usuarioCodigo);
    }

}
