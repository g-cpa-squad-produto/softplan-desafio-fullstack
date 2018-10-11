package br.com.softplan.process.service;

import br.com.softplan.process.exception.ApplicationException;
import br.com.softplan.process.model.Profile;
import br.com.softplan.process.model.User;
import br.com.softplan.process.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;
    private ProfileService profileService;

    @Autowired
    public UserService(UserRepository repository, ProfileService profileService) {
        this.repository = repository;
        this.profileService = profileService;
    }

    public User findById(Long id) throws ApplicationException {
        return repository.findById(id)
                         .orElseThrow(() -> new ApplicationException("Usuário inexistente"));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void save(User user) {
        this.repository.save(user);
    }

    public void delete(Long id) throws ApplicationException {
        User user = findById(id);

        if (!user.getProcesses().isEmpty()) {
            throw new ApplicationException("Usuário ainda possui processos para dar seu parecer");
        }

        this.repository.delete(user);
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public List<User> findAllByProfile(Long id) {
        Profile profile = profileService.findById(id);
        return this.repository.findAllByProfiles(new ArrayList<>(Collections.singleton(profile)));
    }

    public List<User> findByIdIn(List<Long> ids) {
        return this.repository.findByIdIn(ids);
    }
}
