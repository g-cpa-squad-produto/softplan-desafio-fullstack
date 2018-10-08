package br.com.softplan.process.service;

import br.com.softplan.process.model.User;
import br.com.softplan.process.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    User findById(Long id) throws Exception {
        return repository.findById(id)
                         .orElseThrow(() -> new Exception("Usuário inexistente"));
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
