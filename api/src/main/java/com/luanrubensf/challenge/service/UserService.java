package com.luanrubensf.challenge.service;

import com.luanrubensf.challenge.core.validation.Validator;
import com.luanrubensf.challenge.model.Role;
import com.luanrubensf.challenge.model.User;
import com.luanrubensf.challenge.repository.RoleRepository;
import com.luanrubensf.challenge.repository.UserRepository;
import com.luanrubensf.challenge.validators.UserValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    /**
     * Salva um usuário. Antes de salvar o usuário, faz o encode da senha e executa as validações.
     * @param entity Usuário a ser salvo.
     * @param role Nome da role do usuário.
     * @return usuário salvo
     */
    public User save(User entity, String role) {
        entity.setRole(getRole(role));

        validate(entity);

        entity.setPassword(encryptPassword(entity.getPassword()));

        return userRepository.save(entity);
    }

    /**
     * Permite salvar o usuário sem precisar redefinir sua senha. Antes de salvar o usuário, define
     * matchPassword e executa as validações.
     * @param entity Usuário a ser salvo
     * @param role Nome da Role para o usuário
     * @return Usuário salvo
     */
    public User saveWithoutPassword(User entity, String role) {
        entity.setRole(getRole(role));
        entity.setMatchPassword(entity.getPassword());

        validate(entity);

        return userRepository.save(entity);
    }

    /**
     * Busca um objeto Role com base em um nome
     * @param role Nome da Role a ser buscado
     * @return role encontrada
     */
    private Role getRole(String role) {
        Objects.requireNonNull(role, "É necessário informar a role do usuário");
        return roleRepository.findRoleByNameIs(role);
    }

    /**
     * Criptografa senha
     * @param pass Senha a ser criptografada
     * @return senha criptografada
     */
    private String encryptPassword(String pass) {
        return passwordEncoder.encode(pass);
    }

    /**
     * Executa as validações para a entidade User
     * Serão executadas as AbstractValidations e as anotações
     * @param entity User a ser validado
     */
    private void validate(User entity) {
        validator.validate(entity,
                UserValidators.checkMatchPassword(),
                UserValidators.checkEmailDuplicado(userRepository));
    }
}
