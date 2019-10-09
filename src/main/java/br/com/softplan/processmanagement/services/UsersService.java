package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.UsersRepository;
import br.com.softplan.processmanagement.services.exceptions.EmailAlreadyUsedException;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    //CRUD
    public User save(User user) {
        user.setId(null);
        Optional<User> userByEmail = usersRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new EmailAlreadyUsedException("Email already used.");
        }
        return usersRepository.save(user);
    }

    public List<User> list() {
        return usersRepository.findAll();
    }

    public List<User> listFinalizadores() {
        return usersRepository.findAllByType(UserType.FINALIZADOR);
    }

    public User searchById(Long id) {
        Optional<User> user = usersRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    public User update(User user) {
        checkExistence(user);
        return usersRepository.save(user);
    }

    public void delete(Long id) {
        try {
            usersRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Distributor not found");
        }
    }

    public void checkExistence(User user) {
        searchById(user.getId());
    }

}
