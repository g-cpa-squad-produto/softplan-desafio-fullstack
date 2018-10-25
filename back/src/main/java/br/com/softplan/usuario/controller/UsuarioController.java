package br.com.softplan.usuario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.response.Response;
import br.com.softplan.security.JwtUser;
import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.usuario.dto.UsuarioDto;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.service.UsuarioService;
import br.com.softplan.util.ControllerUtil;
import br.com.softplan.util.StringResponse;

/**
 * @author emanuel
 *
 */
@RestController
@RequestMapping("/api-usuario")
public class UsuarioController extends ControllerUtil {
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("usuario")
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	public ResponseEntity<Response<UsuarioDto>> criarNovoUsuario(@Valid @RequestBody Usuario usuario,
			BindingResult result) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();

		if (result.hasErrors()) {
			log.error("Erro validando lancamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		log.info("Criando um novo usuario {}", usuario.getLogin());

		Usuario usuarioSalvo = usuarioService.atualizarOuSalvar(usuario);
		response.setData(new UsuarioDto(usuarioSalvo));
		return ResponseEntity.ok(response);
	}

	@PutMapping("usuario")
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	public ResponseEntity<Response<UsuarioDto>> atualizarUsuario(@Valid @RequestBody Usuario usuario,
			BindingResult result) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();

		if (result.hasErrors()) {
			log.error("Erro validando lancamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		log.info("Criando um novo usuario {}", usuario.getLogin());

		Usuario usuarioSalvo = usuarioService.atualizarOuSalvar(usuario);
		// Se retornar nulo significa que ja existe usuario com esse login
		if (usuarioSalvo != null) {
			response.setData(new UsuarioDto(usuarioSalvo));
			return ResponseEntity.ok(response);
		} else {
			response.getErros().add("Login ja existe");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("usuarios")
	@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'USUARIO_TRIADOR')")
	public ResponseEntity<Response<List<UsuarioDto>>> listarUsuarios() {

		Response<List<UsuarioDto>> response = new Response<List<UsuarioDto>>();
		List<Usuario> usuariosDoBanco = new ArrayList<>();
		// Se for triador retorna apenas usuarios do tipo finalizador
		if (isTriador()) {
			usuariosDoBanco = usuarioService.listarUsuarios(PerfilEnum.ROLE_USUARIO_FINALIZADOR);
		} else {
			usuariosDoBanco = usuarioService.listarUsuarios(null);
		}
		List<UsuarioDto> usuarios = new ArrayList<>();
		usuariosDoBanco.forEach(usuario -> usuarios.add(new UsuarioDto(usuario)));

		response.setData(usuarios);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("usuario")
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	public ResponseEntity<Response<StringResponse>> deletarUsuario(@RequestParam("id") Integer idUsuario) {
		JwtUser user = jwtTokenUtil.getJwtUser(request);
		Response<StringResponse> response = new Response<StringResponse>();
		Usuario usuarioDeletado = usuarioService.excluirUsuario(idUsuario, user.getId().intValue());
		if (usuarioDeletado != null) {
			response.setData(new StringResponse("Usuario excluido"));
			return ResponseEntity.ok(response);
		} else {
			response.getErros().add("Usuario não pode ser deletado");
			return ResponseEntity.badRequest().body(response);
		}
	}
}
