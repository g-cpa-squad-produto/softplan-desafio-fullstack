package br.com.luizgustavo.processevaluation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.processevaluation.model.Process;
import br.com.luizgustavo.processevaluation.model.Report;
import br.com.luizgustavo.processevaluation.model.dto.ProcessDto;
import br.com.luizgustavo.processevaluation.model.form.ProcessForm;
import br.com.luizgustavo.processevaluation.repository.ProcessRepository;
import br.com.luizgustavo.processevaluation.repository.ReportRepository;
import br.com.luizgustavo.processevaluation.repository.UserRepository;
import br.com.luizgustavo.processevaluation.security.util.AuthenticatedUser;

@Service
public class ProcessService {
	
	@Autowired
	private ProcessRepository processRep;
	@Autowired
	private ReportRepository reportRep;
	@Autowired
	private UserRepository userRep;
	
	public ProcessDto insert(ProcessForm form) {
		Process process = form.toEntity(userRep, processRep, reportRep);
		process.setAuthor(AuthenticatedUser.get());
		process = this.processRep.save(process);
		for (Report report : process.getReports()) {
			report.setProcess(process);
		}
		this.reportRep.saveAll(process.getReports());
		return new ProcessDto(process);
	}
	
	public Page<ProcessDto> findAll(Pageable pageable) {
		return this.processRep.findAll(pageable).map(p -> new ProcessDto(p));
	}
	
	public List<ProcessDto> findMyPendings() {
		return this.processRep.findProcessListAuthorAndTextIsNull(AuthenticatedUser.get().getIdUser())
			.stream().map(p -> new ProcessDto(p)).collect(Collectors.toList());
	}
	
	public ProcessDto findById(Long idProcess) {
		Process process = this.processRep.findById(idProcess).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return new ProcessDto(process);
	}
}
