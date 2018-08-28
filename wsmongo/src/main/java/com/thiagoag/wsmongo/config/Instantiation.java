package com.thiagoag.wsmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.thiagoag.wsmongo.DTO.AuthorDTO;
import com.thiagoag.wsmongo.DTO.DecisionDTO;
import com.thiagoag.wsmongo.domain.LegalProcess;
import com.thiagoag.wsmongo.domain.User;
import com.thiagoag.wsmongo.repository.LegalProcessRepository;
import com.thiagoag.wsmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LegalProcessRepository lpRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		lpRepository.deleteAll();
		
		User maria = new User(null, "Maria Silva", "maria@gmail.com");
		User alex = new User(null, "Alex Oliveira", "alex@gmail.com");
		User thiago = new User(null, "Thiago Garcia", "thiago@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, thiago));
		
		LegalProcess process1 = new LegalProcess(null, sdf.parse("25/08/2018"), "17430-0/2018", "Reclamação Trabalhista (...)", 0, new AuthorDTO(alex), null);
		LegalProcess process2 = new LegalProcess(null, sdf.parse("24/07/2017"), "1234-0/2017", "Mandado de segurança na seara trabalhista (...)", 1, new AuthorDTO(alex), null);
		LegalProcess process3 = new LegalProcess(null, sdf.parse("02/01/2015"), "5678-0/2015", "Mandado de segurança na seara trabalhista (...)", 0, new AuthorDTO(alex), null);
		
		DecisionDTO decision = new DecisionDTO("Aborted", "(...) Dessa forma, o juízo a quo entendendo não ter competência para processar e julgar a presente demanda, deveria remeter os autos ao juízo cometente, e não extinguir o processo, razão por que a sentença merece ser anulada.", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		process2.setDecision(decision);
		
		lpRepository.saveAll(Arrays.asList(process1, process2, process3));
	
		alex.getProcesses().addAll(Arrays.asList(process1, process2, process3));
		userRepository.save(alex);
	}

}
