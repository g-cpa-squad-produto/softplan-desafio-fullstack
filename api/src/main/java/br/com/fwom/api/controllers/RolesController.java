package br.com.fwom.api.controllers;

import br.com.fwom.api.models.Role;
import br.com.fwom.api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RolesController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
