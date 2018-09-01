package com.luanrubensf.challenge.validators;

import com.luanrubensf.challenge.core.validation.AbstractValidation;
import com.luanrubensf.challenge.model.User;
import com.luanrubensf.challenge.repository.UserRepository;

public class UserValidators {

    public static AbstractValidation checkMatchPassword() {
        return new AbstractValidation<User>("As senhas informadas não são iguais") {
            @Override
            public boolean validateEntity(User candidate) {
                return candidate.getPassword().equals(candidate.getMatchPassword());
            }
        };
    }

    public static AbstractValidation checkEmailDuplicado(UserRepository userRepository) {
        return new AbstractValidation<User>("Já existe um usuário cadastrado com este e-mail") {
            @Override
            public boolean validateEntity(User candidate) {
                if (candidate.getId() != null) {
                    return !userRepository.existsByEmailAndIdIsNot(candidate.getEmail(), candidate.getId());
                }
                return !userRepository.existsByEmail(candidate.getEmail());
            }
        };
    }
}
