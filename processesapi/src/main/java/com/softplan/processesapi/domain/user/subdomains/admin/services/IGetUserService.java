package com.softplan.processesapi.domain.user.subdomains.admin.services;

import com.softplan.processesapi.domain.user.models.User;

import java.util.List;
import java.util.Optional;

public interface IGetUserService {
    List<User> getAll();
    Optional<User> getOne(Long userId);
    Optional<User> getByEmail(String email);
}
