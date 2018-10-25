/**
 * 
 */
package br.com.softplan.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.softplan.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author emanuel
 *
 */
@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ID = "id_usuario";
	static final String CLAIM_NOME = "nome";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * Retorna o username do token
	 * 
	 * @param token
	 * @return String
	 */
	public String getUserNameToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Retorna o username do token
	 * 
	 * @param token
	 * @return String
	 */
	public JwtUser getJwtUser(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		JwtUser jwtUser;
		try {
			Claims claims = getClaimsFromToken(token);
			jwtUser = new JwtUser(Long.parseLong(claims.get(CLAIM_KEY_ID).toString()), claims.getSubject(), null,
					claims.get(CLAIM_NOME).toString(), null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jwtUser = null;
		}
		return jwtUser;
	}

	/**
	 * Retorna a role do token
	 * 
	 * @param token
	 * @return String
	 */
	public String getRole(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String role;
		try {
			Claims claims = getClaimsFromToken(token);
			role = claims.get(CLAIM_KEY_ROLE).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			role = null;
		}
		return role;
	}

	/**
	 * Retorna o username do token
	 * 
	 * @param token
	 * @return String
	 */
	public Integer getIdUser(String token) {
		Integer idUsuaro;
		try {
			Claims claims = getClaimsFromToken(token);
			idUsuaro = (Integer) claims.get(CLAIM_KEY_ID);
		} catch (Exception e) {
			// TODO: handle exception
			idUsuaro = null;
		}
		return idUsuaro;
	}

	/**
	 * Retorna a data de expiracao do token
	 * 
	 * @param token
	 * @return Date
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			// TODO: handle exception
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Atualiza o token
	 * 
	 * @param token
	 * @return String
	 */
	public String refreshToken(String token) {
		String refreshToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, token);
			refreshToken = gerarToken(claims);
		} catch (Exception e) {
			// TODO: handle exception
			refreshToken = null;
		}
		return refreshToken;
	}

	/**
	 * Verifica se o token ainda e valido
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}

	/**
	 * Retorna um novo Token JWT com base nos dados do usuario
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String obterToken(JwtUser userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_ID, userDetails.getId());
		claims.put(CLAIM_NOME, userDetails.getNome());
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		return gerarToken(claims);
	}

	public JwtUser converterParaUser(String token) {

		Claims claims;
		try {
			claims = getClaimsFromToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			claims = null;
		}

		if (claims != null) {

		}
		return null;
	}

	/**
	 * Realiza o parse do Token para Claims, para extrair as informacoes contidas no
	 * corpo
	 * 
	 * @param token
	 * @return Claims
	 */
	public Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * Verifica se o Token ja foi expirado
	 * 
	 * @param token
	 * @return boolean
	 */
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if (dataExpiracao == null) {
			return false;
		}
		return dataExpiracao.before(new Date());
	}

	/**
	 * Gera um token contendo dados fornecidos
	 * 
	 * @param claims
	 * @return String
	 */
	private String gerarToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				// .setExpiration(gerarDataDeExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
