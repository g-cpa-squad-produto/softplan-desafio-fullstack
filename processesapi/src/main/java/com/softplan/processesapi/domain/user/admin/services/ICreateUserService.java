package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.models.User;

public interface ICreateUserService {
    User post(User user);
}
