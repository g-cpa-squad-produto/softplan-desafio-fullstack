package br.com.luizgustavo.processevaluation.model.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.luizgustavo.processevaluation.model.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDto {
	
	private Long idProcess;	
	private String title;	
	private String description;	
	private String author;	
	private Set<ReportDto> reports = new HashSet<ReportDto>();

	public ProcessDto(Process process) {
		this.idProcess = process.getIdProcess();
		this.title = process.getTitle();
		this.description = process.getDescription();
		this.author = process.getAuthor().getName();
		this.reports = process.getReports().stream().map(r -> new ReportDto(r)).collect(Collectors.toSet());
	}
}
