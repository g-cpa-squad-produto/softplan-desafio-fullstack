package org.gradle;

import org.gradle.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

//	@Autowired
//	UsuarioProcessoService usuarioProcessoService;
	
	@Autowired
	ProcessoService processoService;
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET, produces = "application/json")
	public String teste() {
		return "resultado";
	}
//	
//	@RequestMapping(value = "/analisePorLimtiteCredito", method = RequestMethod.POST, produces = "application/json")
//	public  Analise analisePorLimtiteCredito(@RequestBody Analise analise) {
//		// Analise analiseResultado = analiseService.resultadoConformeLimiteCredito(analise);
//		// return analiseResultado;	
//	}
//	
//	@RequestMapping(value = "/analisePorRisco", method = RequestMethod.POST, produces = "application/json")
//	public  Analise analisePorRisco(@RequestBody Analise analise) {
//		Analise analiseResultado = analiseService.resultadoConformeRisco(analise);
//		return analiseResultado;	
//	}
//	
//	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
//	public void salvarAnalise(@RequestBody Analise analise) {
//		analiseService.salvar(analise);
//	}
//	
	
}
