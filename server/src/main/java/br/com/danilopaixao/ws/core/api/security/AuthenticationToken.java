package br.com.danilopaixao.ws.core.api.security;

import java.util.stream.Collectors;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2877954820905567501L;

    private JwtTokenRaw rawAccessToken;
    private UserLoggedDto userLoggedDto;

    public AuthenticationToken(final JwtTokenRaw unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(Boolean.FALSE);
    }

    public AuthenticationToken(final UserLoggedDto userLoggedDto) {
        super(userLoggedDto.getAuthorities().stream()
        		.map(SimpleGrantedAuthority::new)
        		.collect(Collectors.toList()));

        super.setAuthenticated(Boolean.TRUE);

        this.userLoggedDto = userLoggedDto;
        this.eraseCredentials();
    }

    @Override
    public void setAuthenticated(final boolean authenticated) {
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userLoggedDto;
    }

    @Override
    public void eraseCredentials() {        
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
    
    public UserLoggedDto getUserLoggedIn() {
    	return (UserLoggedDto) this.getPrincipal();
    }
}