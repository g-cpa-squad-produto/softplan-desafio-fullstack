package com.isadora.backendapi.security.jwt;

import java.util.ArrayList;
import java.util.List;

import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.enums.UserEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactory {
	 private JwtUserFactory() {
	    }

	    public static JwtUser create(Usuario user) {
	        return new JwtUser(
	                user.getId(),
	                user.getEmail(),
	                user.getpassword(),
	                mapToGrantedAuthorities(user.getTipo())
	        );
	    }

	    private static List<GrantedAuthority> mapToGrantedAuthorities(UserEnum profileEnum) {
	    		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
	    		authorities.add(new SimpleGrantedAuthority(profileEnum.toString())); 
	    		return   authorities ;
	    }
}
