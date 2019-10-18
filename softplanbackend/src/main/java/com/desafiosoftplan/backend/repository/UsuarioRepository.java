package com.desafiosoftplan.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiosoftplan.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
	
	
}
