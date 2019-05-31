package br.com.softplan.marcusvoltolim.controller;

import br.com.softplan.marcusvoltolim.model.Parecer;
import br.com.softplan.marcusvoltolim.service.ParecerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("parecer")
public class ParecerController extends AbstractController<Parecer> {
	
	private ParecerService parecerService;
	
	@Autowired
	public ParecerController(ParecerService parecerService) {
		super(parecerService);
		this.parecerService = parecerService;
	}
	
	
	@GetMapping("/{filtro}")
	public ResponseEntity<List<Parecer>> findAllBy(@PathVariable String filtro) {
		//		List<Parecer> prods = parecerService.findAllPendentesPorUsuario(new Usuario());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("/last")
	public ResponseEntity<Parecer> getPrecoUltimoProduto() {
		Parecer ultimoProdCadastrado = null;
		if (Objects.isNull(ultimoProdCadastrado)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ultimoProdCadastrado, HttpStatus.OK);
	}
	
}
