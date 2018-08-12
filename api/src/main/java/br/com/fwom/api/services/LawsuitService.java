package br.com.fwom.api.services;

import br.com.fwom.api.models.Lawsuit;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LawsuitService {
    Lawsuit save(Lawsuit lawyer);
    List<Lawsuit> findAll();
    void delete(long id);
    Optional<Lawsuit> findOne(String name);
    Optional<Lawsuit> findById(Long id);
    Set<Lawsuit> findAllOpened(String userEmail);
    Optional<Lawsuit> addAndSaveEditors(Lawsuit lawsuit);
}


