package br.com.processo.prjdemo.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.processo.prjdemo.model.Usuario;

/**
 * 
 * @author Guilherme Sema
 * Serviço para gerar e validar o token de acesso
 */
@Service
public class TokenService {
	
	public static final String TOKEN_SECRET = "t0k3nS3cr3t";
	
	/**
	 * 
	 * @param usr
	 * @return retorna a string com o token com os dados do usuario
	 */
	public String createToken(Usuario usr) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //seta dados que compoe o token
            String token = JWT.create()
                    .withClaim("id", usr.getId().toString())
                    .withClaim("login", usr.getLogin().toString())
                    .withClaim("nome", usr.getNome().toString())
                    .withClaim("createdAt", new Date())
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }
	
	/**
	 * 
	 * @param token
	 * @return recupera usuario pelo token
	 */
	public String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //descriptografa token
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("id").asString();
        } catch (UnsupportedEncodingException exception) {//ERRO
            exception.printStackTrace();
            return null;
        } catch (JWTVerificationException exception) {//FALHA
            exception.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 
	 * @param token
	 * @return valida o token
	 */
	public boolean isTokenValid(String token) {
        String userId = this.getUserIdFromToken(token);
        return userId != null;//SE DIFERENTE DE NULL É VALIDO
    }
}
