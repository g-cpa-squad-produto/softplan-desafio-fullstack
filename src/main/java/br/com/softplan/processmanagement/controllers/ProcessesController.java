package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserProcess;
import br.com.softplan.processmanagement.dto.OpinionDTO;
import br.com.softplan.processmanagement.services.ProcessesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/processes")
@Api(value = "Gerenciamento de processos")
public class ProcessesController {

    @Autowired
    private ProcessesService processesService;

    @ApiOperation(value = "Visualizar a lista de processos cadastrados")
    @GetMapping
    ResponseEntity<List<Process>> list() {
        return ResponseEntity.ok(processesService.list());
    }

    @ApiOperation(value = "Visualizar a lista de processos por criador")
    @GetMapping(value = "/user/{id}")
    ResponseEntity<List<Process>> listByUser(@PathVariable("id") Long idUser) {
        return ResponseEntity.ok(processesService.listByUser(idUser));
    }

    @ApiOperation(value = "Salvando um novo processo")
    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid Process process) {
        process = processesService.save(process);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(process.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Buscar um processo cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<Process> searchById(@PathVariable("id") Long id) {
        Process process = processesService.searchById(id);
        return ResponseEntity.ok(process);
    }

    @ApiOperation(value = "Atualizar dados do processo")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid Process process, @PathVariable("id") Long id) {
        process.setId(id);
        processesService.update(process);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Apagando o processo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        processesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Gravando parecer do processo")
    @PostMapping(value = "/{id}/opinion")
    public ResponseEntity<UserProcess> saveOpinion(@PathVariable("id") Long id, @RequestBody OpinionDTO opinionDTO) {
        UserProcess userProcess = processesService.saveOpinion(id, opinionDTO);
        return ResponseEntity.ok(userProcess);
    }

}