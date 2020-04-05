package com.softplan.processesapi.domain.user.triator.models;

import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TRIATOR")
public class Triator extends User {

    @Override
    public UserType getType() {
        return UserType.TRIATOR;
    }
}
