package br.com.processo.prjdemo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import br.com.processo.prjdemo.service.TokenService;

/**
 * 
 * @author Guilherme Sena
 * Filtro para validar url's
 */
@Configuration
public class JWTFilter extends GenericFilterBean  {
	
	private TokenService tokenService;

    JWTFilter() {
        this.tokenService = new TokenService();
    }

    /**
     * FILTRO PARA VALIDAR SE EXISTE AUTHORIZATION E SE É VALIDA OU SE È TELA DE LOGIN
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");

        //valida se o metodo http é do tipo options
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }
        //valida se é url que nao precisa de autenticacao
        if (allowRequestWithoutToken(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (token == null || !tokenService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                Long userId = new Long(tokenService.getUserIdFromToken(token));
                request.setAttribute("id", userId);
                filterChain.doFilter(req, res);

            }
        }

    }

    /**
     * 
     * @param request
     * @return valida se é tela de login que não exige authorization
     */
    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        if (request.getRequestURI().contains("/login-usuario")) {
            return true;
        }
        return false;    	
    }
}
