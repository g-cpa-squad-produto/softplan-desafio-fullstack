package br.com.softplan.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_profile",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "profile_id", referencedColumnName = "id"))
    private Set<Profile> profiles;

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
}
