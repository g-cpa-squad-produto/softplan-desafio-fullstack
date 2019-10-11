package br.com.softplan.processmanagement.security;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String email;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.setPassword(password);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtRequest{" + "email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }

}