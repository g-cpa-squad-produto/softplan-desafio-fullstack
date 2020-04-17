package com.softplan.backend.dto;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String jwttoken;
    private String username;
    private String role;

    public JwtResponseDTO(String jwttoken, String username, String role) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }
}