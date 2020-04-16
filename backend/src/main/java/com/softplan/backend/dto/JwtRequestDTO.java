package com.softplan.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public JwtRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
