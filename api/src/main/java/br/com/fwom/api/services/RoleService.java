package br.com.fwom.api.services;

import br.com.fwom.api.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    List<Role> findAll();
    void delete(long id);
    Role findOne(String name);
    Optional<Role> findById(Long id);
}
