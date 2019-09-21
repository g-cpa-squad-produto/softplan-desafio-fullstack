package br.com.processos.processo;

import br.com.processos.processo.specification.IProcesso;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/processos")
public class ProcessoAPI {

    @Autowired
    private IProcesso iProcesso;

    @GetMapping(path = "/{processoId}")
    public Processo buscarProcessoPorId(@NotNull @PathVariable Long processoId) {
        return iProcesso.buscarProcessoPorId(processoId);
    }

    @GetMapping
    public List<Processo> buscarTodosProcessos() {
        return iProcesso.buscarTodosProcessos();
    }

    @GetMapping(path = "/pendentes/{usuarioId}")
    public List<Processo> buscarProcessosPendentesParecer(@NotNull @PathVariable Long usuarioId) {
        return iProcesso.buscarProcessosPendentesParecer(usuarioId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Processo inserirProcesso(@Valid @NotNull @RequestBody Processo processo) {
        return iProcesso.inserirProcesso(processo);
    }

    @PostMapping(path = "/{processoId}/pareceres")
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<Parecer> solicitarParecer(@NotNull @PathVariable Long processoId, @NotNull @Valid @RequestBody List<Long> usuariosId) {
        return iProcesso.solicitarParecer(processoId, usuariosId);
    }

    @PutMapping(path = "/{processoId}/parecer/{parecerId}")
    public Parecer realizarParecer(@NotNull @PathVariable Long parecerId, @NotNull @NotEmpty @RequestBody String textoParecer) {
        return iProcesso.realizarParecer(parecerId, textoParecer);
    }
}
