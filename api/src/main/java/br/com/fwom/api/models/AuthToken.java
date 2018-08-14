package br.com.fwom.api.models;

public class AuthToken {
    public AuthToken(String token, String type) {
        this.setToken(token);
        this.setType(type);
    }

    public AuthToken() {
    }

    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
