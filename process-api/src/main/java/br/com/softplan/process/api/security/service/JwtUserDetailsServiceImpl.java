package br.com.softplan.process.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.process.api.entity.User;
import br.com.softplan.process.api.security.jwt.JwtUserFactory;
import br.com.softplan.process.api.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userService.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Usuáriuo não encontrado com email '%s'.", email)); 
		} else {
			return JwtUserFactory.create(user);
		}
	}

}
