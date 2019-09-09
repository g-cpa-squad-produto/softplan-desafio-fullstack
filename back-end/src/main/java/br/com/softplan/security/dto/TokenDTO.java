package br.com.softplan.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class TokenDTO {

    private String token;
    private List<String> roles;

    public TokenDTO(){
    }

    public TokenDTO(String token, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.roles = new ArrayList<>();
        authorities.stream().forEach( authoritie -> roles.add(authoritie.getAuthority().replace("ROLE_", "")));
    }
}
