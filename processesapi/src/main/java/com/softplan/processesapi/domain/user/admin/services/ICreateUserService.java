package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.finisher.models.Finisher;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.triator.models.Triator;

public interface ICreateUserService {
    User createAdmin(Admin newUser);

    User createTriator(Triator newUser);

    User createFinisher(Finisher newUser);
}
