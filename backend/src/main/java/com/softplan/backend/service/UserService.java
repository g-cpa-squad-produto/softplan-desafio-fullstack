package com.softplan.backend.service;

import com.softplan.backend.entity.User;
import com.softplan.backend.enumeration.Role;
import com.softplan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public List<User> findFinUsers(){
        return userRepository.findByRole(Role.FINALIZADOR);
    }

    public User findUserById(Long id) throws Exception {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e){
            throw new Exception("Nome de usuário já existe");
        }
    }

    public User newUser(User user) throws Exception {
        try {
            user.setPassword( passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e){
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
