package br.com.softplan.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Id
    @Column
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull(message = "Campo obrigatório")
    private String name;

    @Column
    @NotNull(message = "Campo obrigatório")
    private String email;

    @Column
    @NotNull(message = "Campo obrigatório")
    private String password;

    @NotNull(message = "Campo obrigatório")
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_profile",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
    private Set<Profile> profiles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_process",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "process_id", referencedColumnName = "id"))
    private Set<Process> processes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>(0);
        this.profiles.forEach((profile) -> roles.addAll(profile.getRoles()));

        return roles;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    protected void onCreate() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(this.password);
        this.setCreatedAt(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(this.password);
        this.setUpdatedAt(new Date());
    }
}
