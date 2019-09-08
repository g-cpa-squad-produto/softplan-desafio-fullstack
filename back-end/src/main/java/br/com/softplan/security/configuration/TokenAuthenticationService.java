package br.com.softplan.security.configuration;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864000000;
    static final String SECRET = "MySecreteApp";
    static final String TOKEN_PREFIX = "Softplan";
    static final String AUTHORIZATION_FIELD = "Authorization";

    private TokenAuthenticationService(){

    }

    public static void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String jwtToken = buildJwtToken(username);
        addTokenToHeader(response, username);
        addTokenToBody(response, jwtToken);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        String token = getAuthorizationFromHeader(request);

        if (Objects.isNull(token)) {
            return null;
        }

        String user = getUserFromToken(token);
        return instanceAuthentication(user);
    }

    private static String buildJwtToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private static void addTokenToHeader(HttpServletResponse response, String jwtToken){
        response.addHeader(AUTHORIZATION_FIELD, TOKEN_PREFIX + " " + jwtToken);
    }
    private static void addTokenToBody(HttpServletResponse response, String jwtToken) throws IOException {

        Map<String, String> tokenObj = new HashMap<>();
        tokenObj.put("token", jwtToken);

        response.getWriter().write(new Gson().toJson(tokenObj));
    }

    private static String getAuthorizationFromHeader(HttpServletRequest request){
        return request.getHeader(AUTHORIZATION_FIELD);
    }

    private static String getUserFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }

    private static Authentication instanceAuthentication(String user){
        if(Objects.isNull(user)){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user, null, emptyList());
    }
}
