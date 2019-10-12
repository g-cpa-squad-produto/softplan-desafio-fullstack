package br.com.softplan.processmanagement.security;

import br.com.softplan.processmanagement.domain.UserAuthentication;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersSystemRepository usersSystemRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserSystem> userAppOptional = usersSystemRepository.findByEmail(email);
        if(!userAppOptional.isPresent()){
            throw new UsernameNotFoundException("User not found with email: " + email);
        };
        UserSystem userSystem = userAppOptional.get();
        return new UserAuthentication(userSystem, getPermissions(userSystem));
    }

    public Collection<? extends GrantedAuthority> getPermissions(UserSystem userSystem) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        //não estou trabalhando com permissões neste projeto
        List<String> permissions = new ArrayList<>();
        permissions.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
        return authorities;
    }

}