package br.com.softplan.parecer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.parecer.dto.ParecerDto;
import br.com.softplan.parecer.modelos.Parecer;
import br.com.softplan.parecer.service.ParecerService;
import br.com.softplan.processo.controller.ProcessoController;
import br.com.softplan.response.Response;
import br.com.softplan.security.JwtUser;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.util.ControllerUtil;

/**
 * @author emanuel
 *
 */
@RestController
@RequestMapping("/api-parecer")
public class ParecerController extends ControllerUtil {
	private static final Logger log = LoggerFactory.getLogger(ProcessoController.class);

	@Autowired
	private ParecerService parecerService;

	@PostMapping("/parecer")
	@PreAuthorize("hasRole('USUARIO_FINALIZADOR')")
	public ResponseEntity<Response<ParecerDto>> incluirNovoParecer(@Valid @RequestBody Parecer parecer,
			BindingResult result) {

		Response<ParecerDto> response = new Response<ParecerDto>();

		if (result.hasErrors()) {
			log.error("Erro validando lancamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		log.info("Criando um novo parecer {}", parecer.getNome());

		// Seta o usuario do parecer pelo token do usuario
		JwtUser user = jwtTokenUtil.getJwtUser(request);
		parecer.setUsuario(new Usuario(user.getId().intValue()));
		Parecer parecerSalvo = parecerService.incluirParecer(parecer);

		response.setData(new ParecerDto(parecerSalvo));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/parecer")
	@PreAuthorize("hasAnyRole('USUARIO_FINALIZADOR','USUARIO_TRIADOR')")
	public ResponseEntity<Response<List<ParecerDto>>> listarParecerPorProcesso(
			@RequestParam("idProcesso") Integer idProcesso) {
		Response<List<ParecerDto>> response = new Response<List<ParecerDto>>();

		List<Parecer> pareceresDoBanco = parecerService.listarParecerPorProcesso(idProcesso);
		List<ParecerDto> pareceres = new ArrayList<>();
		pareceresDoBanco.forEach(parecer -> pareceres.add(new ParecerDto(parecer)));

		response.setData(pareceres);
		return ResponseEntity.ok(response);
	}

}
