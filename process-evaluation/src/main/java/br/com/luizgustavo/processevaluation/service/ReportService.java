package br.com.luizgustavo.processevaluation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.processevaluation.model.Report;
import br.com.luizgustavo.processevaluation.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRep;

	public void addTextReport(Long idReport, String text) {
		Report report = this.reportRep.findById(idReport).orElseThrow(() -> new EmptyResultDataAccessException(1));
		report.setText(text);
		report = this.reportRep.save(report);
	}
}
