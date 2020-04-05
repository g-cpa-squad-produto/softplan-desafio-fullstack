package com.softplan.processesapi.domain.user.subdomains.admin.services;

import com.softplan.processesapi.domain.user.models.User;

public interface ICreateUserService {
    User createUser(User newUser);
}
