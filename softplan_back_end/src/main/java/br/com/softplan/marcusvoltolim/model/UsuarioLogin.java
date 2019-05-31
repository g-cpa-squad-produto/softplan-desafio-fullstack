package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.enums.Permissao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@Data
public class UsuarioLogin extends User {
	
	public UsuarioLogin(String username, String password, String permissao) {
		super(username, password,
				Collections.singleton(new SimpleGrantedAuthority(Permissao.valueOf(permissao).name())));
	}
	
}
