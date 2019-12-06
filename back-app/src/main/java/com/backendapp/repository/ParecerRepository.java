package com.backendapp.repository;

import com.backendapp.model.Parecer;
import com.backendapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {

    @Modifying
    @Query(
            value = "UPDATE parecer p SET p.usuario_id=null WHERE p.usuario_id = ?1",
            nativeQuery = true)
    int updateParecer(Long idUsuario);

}
