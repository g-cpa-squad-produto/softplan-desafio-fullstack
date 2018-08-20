package com.processos.luiz.resources;

import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.processos.luiz.exception.UsuarioException;
import com.processos.luiz.models.Usuario;
import com.processos.luiz.repository.UsuarioRepository;
import com.processos.luiz.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="API REST Usuario de Processos")
@RestController
@RequestMapping("/v1")
public class UsuarioResource {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	@ApiOperation(value="Retorna lista de Usuario")
	@GetMapping(path="protected/usuarios", produces="application/json")
	public @ResponseBody  ArrayList<Usuario> listaUsuarios()throws UsuarioException{
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioRepository.findAll();
		return usuarios;
	}
	@ApiOperation(value="Retorna um usuario específico")
	@GetMapping(path="protected/usuarios/{codigo}", produces="application/json")
	public @ResponseBody Usuario usuario(@PathVariable(value="codigo") long codigo)throws UsuarioException{
		Usuario usuario = usuarioRepository.findByCodigo(codigo);
		return usuario;
	}
	@ApiOperation(value="Salva um usuario")
	@PostMapping(path="admin/usuarios")
	public Usuario cadastraUsuario(@RequestBody @Valid Usuario usuario)throws UsuarioException{
		usuarioService.salvarUsuario(usuario);
		return usuario;
	}
	
	@ApiOperation(value="Deleta um usuario")
	@PreAuthorize(value = "ROLE_ADMIN")
	@DeleteMapping(path="admin/usuarios/{codigo}")
	public String deletaUsuario(@PathVariable(value="codigo") long codigo) throws UsuarioException{
		Usuario usuario = usuarioRepository.getOne(codigo);
		if(usuario!=null){
			usuarioRepository.delete(usuario);
		}
		return "Usuario excluido com sucesso";
	}
	@ApiOperation(value="Altera senha usuario logado")
	@PutMapping(path="admin/usuarios/{login}/{senhaNova}")
	public String alterarSenhaUsuario(@PathVariable(value="login") String login,
			@PathVariable(value="senhaNova") String senhaNova) throws UsuarioException{
		String mensagem = usuarioService.alterarSenhaUsuario(login, senhaNova);
		return mensagem;
	}
}
