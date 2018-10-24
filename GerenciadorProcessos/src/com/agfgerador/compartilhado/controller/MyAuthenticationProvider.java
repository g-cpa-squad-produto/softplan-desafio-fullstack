package com.agfgerador.compartilhado.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;

import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.service.UsuarioService;
import com.agfgerador.compartilhado.util.AGFUtil;

@Controller
@Scope("request")
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    private UsuarioService usuarioService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
    	Authentication autentication; 
    	String login = (String) authentication.getPrincipal();
    	String senha = AGFUtil.MD5((String) authentication.getCredentials());
    	
        if((login == null) || (login.equals(""))){
            throw new AuthenticationServiceException("Erro na localização do LOGIN!!!");
        }

        Usuario usuario = usuarioService.findByAutentication(login, senha);

        if(usuario!=null)
        	autentication = getAutentication(usuario);
        else
        {
        	String senhaRedirect = (String) authentication.getCredentials();
        	if((senhaRedirect!=null)&&(!senhaRedirect.equals(""))&&(senhaRedirect.contains("@"))){	
        		int pin = getPin(senhaRedirect);
        		senhaRedirect = senhaRedirect.substring(0, (senhaRedirect.indexOf("@")));
        		senhaRedirect = getSenhaId(pin, senhaRedirect);
        	}	
        	usuario = usuarioService.findByAutentication(login, senhaRedirect);
        	autentication = getAutentication(usuario);
        }
        
        return autentication;
        
    }
    
    public Authentication getAutentication(Usuario user)
    {	try{
    		if(user!=null)
        		return new MyAuthenticationToken(user.getLogin(), user.getSenha(), getPermissoes());
        	else{
                throw new AuthenticationServiceException("Usuário não autenticado.");
            }
    	}catch(AuthenticationServiceException e){
            throw e;
        }catch(Throwable e){
            throw new AuthenticationServiceException("Ocorreu um erro no ato da autenticação.", e);
        }
    	
    	
    }

    public List<GrantedAuthority> getPermissoes()
    {	List<GrantedAuthority> permissoes = new ArrayList<GrantedAuthority>();
   	 	GrantedAuthority permissao = new GrantedAuthorityImpl("ROLE_USER");
   	 	permissoes.add(permissao);
   	 	return permissoes;
    }
    
    public String getSenhaId(int fim,String senha){
		String senhaU="";
		senhaU = senha.substring(0,fim)+senha.substring(fim+1,senha.length());
		return senhaU;
	}
	
    
    @Override
    public boolean supports(Class<? extends Object> authentication){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
                && authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    public int getPin(String senha)
    {	String pin = senha.substring((senha.indexOf("@")+1), senha.length());
    	return Integer.parseInt(pin);
    }
}
