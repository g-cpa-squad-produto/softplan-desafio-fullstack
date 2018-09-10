package br.com.softplan.feature.processo;

import br.com.softplan.core.controller.AbstractController;
import br.com.softplan.feature.processo.dto.ProcessoDTO;
import br.com.softplan.feature.processo.dto.ProcessoResumidoDTO;
import br.com.softplan.feature.processo.model.Processo;
import br.com.softplan.feature.processo.model.StatusProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
