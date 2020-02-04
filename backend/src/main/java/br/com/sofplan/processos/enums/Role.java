package br.com.sofplan.processos.enums;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {

    TRIADOR("TRIADOR"),
    FINALIZADOR("FINALIZADOR"),
    ADMIN("ADMIN");

    private String authority;

    Role(String value) {
        this.authority = value;
    }

    public static Role from(String role) {
        return Arrays.asList(Role.values()).stream().filter(r -> r.getAuthority().equals(role))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
