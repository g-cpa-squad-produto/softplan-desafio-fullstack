package com.softplan.teste.process.repository;

import com.softplan.teste.process.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpinionRepository  extends JpaRepository<Opinion, Long> {
    Optional<Opinion> findByText(String text);
}
