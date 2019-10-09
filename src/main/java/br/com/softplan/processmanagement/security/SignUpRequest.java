package br.com.softplan.processmanagement.security;

import br.com.softplan.processmanagement.domain.UserType;

import java.io.Serializable;

public class SignUpRequest implements Serializable {

    private static final long serialVersionUID = 6607920523289221002L;
    private String name;
    private String email;
    private UserType userType;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}