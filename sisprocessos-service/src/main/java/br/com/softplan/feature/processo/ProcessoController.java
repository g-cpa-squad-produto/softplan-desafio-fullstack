package br.com.softplan.feature.processo;

import br.com.softplan.core.controller.AbstractController;
import br.com.softplan.core.exception.NegocioException;
import br.com.softplan.feature.processo.dto.ProcessoDTO;
import br.com.softplan.feature.processo.dto.ProcessoResumidoDTO;
import br.com.softplan.feature.processo.model.Processo;
import br.com.softplan.feature.processo.model.StatusProcesso;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController extends AbstractController<Processo, ProcessoDTO, ProcessoResumidoDTO, Long, ProcessoService, ProcessoMapper> {

    @Autowired
    public ProcessoController(ProcessoService service, ProcessoMapper mapper) {
        super(service, mapper);
    }

    @GetMapping(path = "/status")
    public ResponseEntity<StatusProcesso[]> listaStatusProcessos() {
        return ResponseEntity.ok(StatusProcesso.values());
    }

    @PutMapping(path = "/atribuir-usuarios/{idProcesso}")
    public ResponseEntity<?> atribuirUsuariosAProcesso(@RequestBody List<Long> idUsuarios, @PathVariable Long idProcesso) throws NegocioException {
        service.atribuirUsuariosAProcesso(idProcesso, idUsuarios);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/incluir-parecer/{idProcesso}")
    public ResponseEntity<?> incluirParecer(@RequestBody JsonNode textoParecer, @PathVariable Long idProcesso) throws NegocioException {
        service.incluirParecer(idProcesso, textoParecer.get("textoParecer").asText());
        return ResponseEntity.ok().build();
    }

}
