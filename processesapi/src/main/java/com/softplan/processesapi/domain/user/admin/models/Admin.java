package com.softplan.processesapi.domain.user.admin.models;

import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
