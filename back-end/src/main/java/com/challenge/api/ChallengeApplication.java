package com.challenge.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.challenge.api.documents.Role;
import com.challenge.api.repositories.RoleRepository;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {
	    	/*  - Visão de administrador
	    	 *    Incluir, excluir, atualizar e visualizar usuários
			*/
	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }
	        
	        /*  - Visão de usuário-triador
			 *    Incluir e visualizar processos
			 *	  Atribuir um ou mais usuários a realizar um parecer sobre um processo
			*/
	        Role superUserRole = roleRepository.findByRole("SUPERUSER");
	        if (superUserRole == null) {
	            Role newSuperUserRole = new Role();
	            newSuperUserRole.setRole("SUPERUSER");
	            roleRepository.save(newSuperUserRole);
	        }
	        
	        /*  - Visão de usuário-finalizador
			 *	  Visualizar processos pendentes de parecer.
			 *	  Incluir o parecer sobre o processo.
			*/
	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	        
	        
	    };

	}

}
