package br.com.luizgustavo.processevaluation.model.form;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.luizgustavo.processevaluation.model.Process;
import br.com.luizgustavo.processevaluation.repository.ProcessRepository;
import br.com.luizgustavo.processevaluation.repository.ReportRepository;
import br.com.luizgustavo.processevaluation.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessForm {

	@NotNull @NotBlank @Length(max = 20)
	private String title;
	@NotNull @NotBlank @Length(max = 256)
	private String description;
	@NotEmpty
	private Set<ReportForm> reports = new HashSet<>();
	
	public Process toEntity(UserRepository userRep, ProcessRepository processRep, ReportRepository reportRep) {
		Process process = new Process();
		process.setTitle(this.title);
		process.setDescription(this.description);
		process.setReports(this.reports.stream().map(r -> r.toEntity(userRep, processRep)).collect(Collectors.toSet()));
		return process;		
	}
}
