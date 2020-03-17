package br.com.luizgustavo.processevaluation.model.form;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.luizgustavo.processevaluation.model.Report;
import br.com.luizgustavo.processevaluation.model.User;
import br.com.luizgustavo.processevaluation.repository.ProcessRepository;
import br.com.luizgustavo.processevaluation.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportForm {
	
	private String text;
	private Long author;
	private Long process;
    
    public Report toEntity(UserRepository userRep, ProcessRepository processRep) {
    	Report report = new Report();
    	report.setText(this.text);
    	User user = userRep.findById(this.author).orElseThrow(() -> new EmptyResultDataAccessException(1));
    	report.setAuthor(user);    	
    	return report;
    }
}
