package br.com.softplan.processmanagement.security.jwt;

import br.com.softplan.processmanagement.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            String jwtToken = jwtTokenProvider.resolveToken(request);

            if (!StringUtils.hasText(jwtToken) || !jwtTokenProvider.validateToken(jwtToken)) {
                chain.doFilter(request, response);
                logger.info("Continue ...");
                return;
            }

            logger.info("Request: " + request);
            logger.info("JWT Token Login: " + jwtToken);

            String useremail = jwtTokenProvider.getUsernameFromToken(jwtToken);
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(useremail);

            if (!jwtTokenProvider.validateToken(jwtToken, userDetails)) {
                chain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        chain.doFilter(request, response);
    }
}