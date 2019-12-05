package com.backendapp.repository;

import com.backendapp.model.Parecer;
import com.backendapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {
}
