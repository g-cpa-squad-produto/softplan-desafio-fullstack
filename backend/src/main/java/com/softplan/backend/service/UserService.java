package com.softplan.backend.service;

import com.softplan.backend.entity.User;
import com.softplan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User newUser(User user) throws Exception {
        try {
            user.setPassword( passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception e){
            throw new Exception("Nome de usuário já existe");
        }
    }

    public User updateUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            throw new Exception("ID não encontrado");
        }
    }

    public void deleteUser(Long id) throws Exception {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("ID não encontrado");
        }
    }

}
