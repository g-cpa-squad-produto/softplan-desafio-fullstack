package br.com.sofplan.processos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansConfiguration {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encode = encoder.encode("123456");
//		System.out.println(encode);
//	}
	
}
