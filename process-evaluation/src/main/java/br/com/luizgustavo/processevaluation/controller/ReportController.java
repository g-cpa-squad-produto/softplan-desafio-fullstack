package br.com.luizgustavo.processevaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgustavo.processevaluation.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_CLOSER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addTextReport(@PathVariable("id") Long idReport, @RequestBody String text) {
		this.reportService.addTextReport(idReport, text);
	}	
	
}
