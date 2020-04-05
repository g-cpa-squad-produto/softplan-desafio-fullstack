package com.softplan.processesapi.domain.user.enums;

public enum UserType {
    ADMIN("ADMIN"), TRIATOR("TRIATOR"), FINISHER("FINISHER");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
