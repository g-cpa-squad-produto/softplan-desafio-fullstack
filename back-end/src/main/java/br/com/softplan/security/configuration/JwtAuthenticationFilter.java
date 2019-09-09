package br.com.softplan.security.configuration;

import br.com.softplan.security.exception.NoTokenInAuthorizationField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static br.com.softplan.security.configuration.JwtConstants.HEADER_AUTHORIZATION_FIELD;
import static br.com.softplan.security.configuration.JwtConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = getHeaderAuthorizationField(request);

        if(Objects.nonNull(header)) {

            String authToken = getAuthToken(header);
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(authToken, userDetails))) {
                    createUserNameAuthenticationToken(authToken, username, userDetails, request);
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void createUserNameAuthenticationToken(String authToken, String username, UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.info("authenticated user " + username + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static String getAuthToken(String header) {
        String authToken = header.replace(TOKEN_PREFIX, "");
        if(Objects.isNull(authToken)){
            throw new NoTokenInAuthorizationField();
        }
        return authToken;
    }

    private static String getHeaderAuthorizationField(HttpServletRequest request) {
        return  request.getHeader(HEADER_AUTHORIZATION_FIELD);
    }
}
