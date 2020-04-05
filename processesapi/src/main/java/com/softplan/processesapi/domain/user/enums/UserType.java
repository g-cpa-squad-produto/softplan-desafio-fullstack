package com.softplan.processesapi.domain.user.enums;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.finisher.models.Finisher;
import com.softplan.processesapi.domain.user.triator.models.Triator;

public enum UserType {
    ADMIN("ADMIN", Admin.class), TRIATOR("TRIATOR", Triator.class), FINISHER("FINISHER", Finisher.class);

    private String name;
    private Class entityClass;

    UserType(String name, Class entityClass) {
        this.name = name;
        this.entityClass = entityClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
