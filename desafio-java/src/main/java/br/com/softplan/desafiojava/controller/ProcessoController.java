package br.com.softplan.desafiojava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.business.ProcessoBusiness;
import br.com.softplan.desafiojava.entity.Processo;
import br.com.softplan.desafiojava.entity.Usuario;

@RestController
public class ProcessoController {

	@Autowired
	ProcessoBusiness business;
	
	@GetMapping("/processo")
	public List<Processo> listarTodos() {
		return business.listarTodos();
	}
	
	@GetMapping("/processo/{id}")
	public Processo consultar(@PathVariable Long id) {
		return business.consultar(id);
	}
	
	/**
	 * Processos pendentes de um usuário
	 * @param id Identificador do usuário que está solicitando a lista
	 * @return Lista de processos
	 */
	@GetMapping("/processo/usuario/{id}")
	public List<Processo> listarPorUsuario(@PathVariable Long id) {
		return business.listarPorUsuario(id);
	}
	
	@PostMapping("/processo")
	public void incluir(@RequestBody Processo processo) {
		business.incluir(processo);
	}
	
	/**
	 * Associa o(s) usuário(s) ao processo informado 
	 * @param listaUsuarios Lista de usuários a serem associados
	 * @param id Identificador do processo.
	 */
	@PatchMapping("/processo/{id}")
	public Processo associarUsuarios(@RequestBody List<Usuario> listaUsuarios, @PathVariable Long id) {
		return business.associarUsuarios(listaUsuarios, id);
	}
}
