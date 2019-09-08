package br.com.softplan.security.configuration;

import br.com.softplan.security.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse res) throws AuthenticationException, IOException {

        Usuario usuario = getUsuario(request);

        log.info("User logged in: " + usuario.getEmail());

        return instanceAuthentication(usuario);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        TokenAuthenticationService
                .addAuthentication(response, authentication.getName());
    }

    private Authentication instanceAuthentication(Usuario usuario){
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(),
                        usuario.getSenha(),
                        Collections.emptyList()
                )
        );
    }

    private static Usuario getUsuario(HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        return gson.fromJson(json, Usuario.class);
//        return new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
    }
}