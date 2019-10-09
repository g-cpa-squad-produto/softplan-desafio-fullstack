package br.com.softplan.processmanagement.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAuthentication extends User {

    private static final long serialVersionUID = 1L;
    private UserSystem userApp;

    public UserAuthentication(UserSystem userApp, Collection<? extends GrantedAuthority> authorities) {
        super(userApp.getEmail(), userApp.getPassword(), authorities);
        this.userApp = userApp;
    }

    public UserSystem getUser() {
        return userApp;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserSystem getUserApp() {
        return userApp;
    }

    public void setUserApp(UserSystem userApp) {
        this.userApp = userApp;
    }

}
