package br.com.softplan.process.service;

import br.com.softplan.process.exception.ApplicationException;
import br.com.softplan.process.model.Profile;
import br.com.softplan.process.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository repository;

    @Autowired
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> findByIdIn(List<Long> ids) {
        return this.repository.findByIdIn(ids);
    }

    public List<Profile> findAll() {
        return repository.findAll();
    }

    public Profile findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ApplicationException("Perfil inexistente"));
    }
}
