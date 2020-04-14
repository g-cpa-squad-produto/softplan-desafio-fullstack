package com.softplan.processmanagerapi.payload.response;

import com.softplan.processmanagerapi.models.enums.RoleType;

public class RoleResponse {
    private RoleType role;
    private String profile;

    public RoleResponse() {
    }

    public RoleResponse(RoleType role, String profile) {
        this.role = role;
        this.profile = profile;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

}
