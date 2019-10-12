package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.security.ApiResponse;
import br.com.softplan.processmanagement.services.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/opinions")
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @GetMapping
    ResponseEntity<List<Opinion>> list() {
        return ResponseEntity.ok(opinionService.list());
    }

    @PostMapping
    ResponseEntity<ApiResponse> save(@RequestBody @Valid Opinion Opinion) {
        Opinion = opinionService.save(Opinion);
        return ResponseEntity.ok(new ApiResponse(true, "Opinion created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opinion> searchById(@PathVariable("id") Long id) {
        Opinion opinion = opinionService.searchById(id);
        return ResponseEntity.ok(opinion);
    }

    @PutMapping(value = "/{id}/process/{idProcess}")
    public ResponseEntity<ApiResponse> updateOpinion(@PathVariable("id") Long id, @PathVariable("idProcess") Long idProcess, @RequestBody Opinion opinion) {
        opinion.setId(id);
        opinionService.update(idProcess, opinion);
        return ResponseEntity.ok(new ApiResponse(true, "Opinion updated"));
    }

    @GetMapping(value = "/process/{idProcess}")
    public ResponseEntity<List<Opinion>> getOpinionsByProcess(@PathVariable("idProcess") Long idProcess) {
        List<Opinion> opinions = opinionService.getOpinionsByProcess(idProcess);
        return ResponseEntity.ok(opinions);
    }

}