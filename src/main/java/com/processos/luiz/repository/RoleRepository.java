package com.processos.luiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.processos.luiz.models.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	public Role findByNomeRole(String nomeRole);
}
