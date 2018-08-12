package br.com.fwom.api.services;

import br.com.fwom.api.models.LawsuitUser;
import br.com.fwom.api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetails findByEmail(String email);
    User save(User user);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    void delete(long id);
    User findOne(String email);
    Optional<User> findById(Long id);
    List<LawsuitUser> findAssociations(Long lawsuitId, Long userId);
}
