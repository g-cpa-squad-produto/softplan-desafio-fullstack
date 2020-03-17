package br.com.luizgustavo.processevaluation.model.dto;

import br.com.luizgustavo.processevaluation.model.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
	
	private Long idReport;
	private String text;
	private String author;
    private Long process;
    
    public ReportDto(Report report) {
    	this.idReport = report.getIdReport();
    	this.text = report.getText();
    	this.author = report.getAuthor().getName();
    	this.process = report.getProcess().getIdProcess();
    }
	
}
