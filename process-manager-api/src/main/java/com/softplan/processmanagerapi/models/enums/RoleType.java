package com.softplan.processmanagerapi.models.enums;

import com.softplan.processmanagerapi.payload.response.RoleResponse;

import java.util.ArrayList;
import java.util.List;

public enum RoleType {
    ROLE_USER_SCREENER("Triador"),
    ROLE_USER_FINISHER("Finalizador"),
    ROLE_ADMIN("Administrador");

    private String profile;

    RoleType(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }


    public static List<RoleResponse> getAllRoles() {
        List<RoleResponse> roles = new ArrayList<>();
        for (RoleType role: RoleType.values()) {
            roles.add(new RoleResponse(role, role.getProfile()));
        }
        return roles;
    }
}
