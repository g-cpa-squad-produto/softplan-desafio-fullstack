package br.com.fwom.api.models;

public class AuthToken {
    public AuthToken(String token) {
        this.setToken(token);
    }

    public AuthToken() {
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
