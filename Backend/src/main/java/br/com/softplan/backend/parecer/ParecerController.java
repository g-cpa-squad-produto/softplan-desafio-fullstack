package br.com.softplan.backend.parecer;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ParecerController {

	private final ParecerService parecerService;

	public ParecerController(ParecerService parecerService) {
		this.parecerService = parecerService;
	}

	@PostMapping("/parecer")
	public ResponseEntity addParecer(@RequestBody ParecerRequest parecerRequest) {
		log.info("addParecer request : {}", parecerRequest);
		parecerService.saveParecer(parecerRequest.toParecerModel());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/parecer/{parecerId}")
	public ResponseEntity editParecer(@RequestBody ParecerModel parecerModel, @PathVariable String parecerId){
		parecerModel.setParecerId(parecerId);
		parecerService.updateParecer(parecerModel);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/pareceres")
	public List<ParecerModel> getAllPareceres(@RequestHeader(value="usuarioId") String usuarioId) {
		return parecerService.findAll(usuarioId);
	}

	@GetMapping("/parecer/{parecerId}")
	public ParecerModel getParecerById(@PathVariable String parecerId, @RequestHeader(value="usuarioId") String usuarioId) {
		return parecerService.findById(parecerId, usuarioId);
	}

	@GetMapping("/parecer/processo/{processoId}")
	public List<ParecerModel> getParecerById(@PathVariable String processoId) {
		return parecerService.findByProcesso(processoId);
	}
}
