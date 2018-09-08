package br.com.sitalobr.dev.desafio.dto;

import java.io.Serializable;

public class UsuarioCadastroDTO implements Serializable {
    private static final long serialVersionUID = 2167414995894556065L;

    private String nome;
    private String username;
    private String password;
    private String roleName;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
