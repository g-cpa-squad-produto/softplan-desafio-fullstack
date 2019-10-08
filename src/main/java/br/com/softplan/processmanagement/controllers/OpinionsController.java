package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.services.OpinionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/opinions")
public class OpinionsController {

    @Autowired
    private OpinionsService opinionsService;

    @ApiOperation(value = "Visualizar a lista de processos por criador")
    @GetMapping(value = "/user/{idUser}/process/{idProcess}")
    ResponseEntity<?> listByCreator(@PathVariable("idUser") Long idUser, @PathVariable("idProcess") Long idProcess){
        return ResponseEntity.ok(opinionsService.findByProcessAndUser(idUser, idProcess));
    }

}
