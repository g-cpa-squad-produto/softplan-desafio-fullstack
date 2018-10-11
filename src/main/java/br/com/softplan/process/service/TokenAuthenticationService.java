package br.com.softplan.process.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.softplan.process.model.Role;
import br.com.softplan.process.model.User;
import br.com.softplan.process.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 28_800_000; // 8hs
    private static final String SECRET = "MySecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String JWT = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        UserResponse userResponse = buildToUserResponse(JWT, user);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(mapper.writeValueAsString(userResponse));
        response.getWriter().flush();
        response.getWriter().close();
    }

    public static Authentication getAuthentication(HttpServletRequest request, UserService userService) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String email = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            User user = userService.findByEmail(email);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            }
        }

        return null;
    }

    private static UserResponse buildToUserResponse(String token, User user) {
        UserResponse userResponse = new UserResponse();

        List<Role> roles = (List<Role>) user.getAuthorities();
        List<String> namedRoles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        userResponse.setEmail(user.getEmail());
        userResponse.setToken(token);
        userResponse.setName(user.getName());
        userResponse.setRoles(namedRoles);

        return userResponse;
    }
}
