package br.com.softplan.process.service;

import br.com.softplan.process.model.User;
import br.com.softplan.process.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) throws Exception {
        return repository.findById(id)
                         .orElseThrow(() -> new Exception("Usuário inexistente"));
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void save(User user) {
        this.repository.save(user);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public List<User> findByIdIn(List<Long> ids) {
        return this.repository.findByIdIn(ids);
    }
}
