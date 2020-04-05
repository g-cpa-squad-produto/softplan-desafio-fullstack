package com.softplan.processesapi.domain.user.finisher.models;

import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FINISHER")
public class Finisher extends User {

    @Override
    public UserType getType() {
        return UserType.FINISHER;
    }
}

