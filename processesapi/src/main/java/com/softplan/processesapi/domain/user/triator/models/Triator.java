package com.softplan.processesapi.domain.user.triator.models;

import com.softplan.processesapi.domain.user.enums.UserType;
import com.softplan.processesapi.domain.user.models.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TRIATOR")
public class Triator extends User {

    public Triator() {
    }

    public Triator(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

    @Override
    public UserType getType() {
        return UserType.TRIATOR;
    }
}
