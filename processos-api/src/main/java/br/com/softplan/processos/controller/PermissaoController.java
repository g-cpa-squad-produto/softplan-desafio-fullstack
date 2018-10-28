package br.com.softplan.processos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.processos.model.Permissao;
import br.com.softplan.processos.service.ServicoPermissao;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private ServicoPermissao servicoPermissao;

    @GetMapping
    public ResponseEntity<List<Permissao>> selecionarTodos() {
	return ResponseEntity.ok().body(servicoPermissao.selecionarTodas());
    }
}
