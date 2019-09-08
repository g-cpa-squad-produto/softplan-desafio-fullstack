package br.com.softplan.security.configuration;

public class JwtConstants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "MySecreteApp";
    public static final String TOKEN_PREFIX = "Softplan ";
    public static final String HEADER_AUTHORIZATION_FIELD = "Authorization";
    public static final String AUTHORITIES_KEY = "permissoes";
}
