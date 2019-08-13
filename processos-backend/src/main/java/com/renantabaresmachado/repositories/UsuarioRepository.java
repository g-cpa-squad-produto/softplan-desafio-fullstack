package com.renantabaresmachado.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.renantabaresmachado.domains.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly=true)
	Usuario findByEmail(String email);
	

}
