package com.luanrubensf.challenge.repository;

import com.luanrubensf.challenge.model.Processo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessoRepository extends CrudRepository<Processo, Long> {
    List<Processo> findAllBy();

    Processo findProcessoById(Long id);

    boolean existsByNameAndIdIsNot(String name, Long id);

    boolean existsByName(String name);
}
