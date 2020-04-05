package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.models.User;

import java.util.List;
import java.util.Optional;

public interface IGetUserService {
    List<User> getAll();
    Optional<User> getOne(Long userId);
}
