package com.softplan.processmanagerapi.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpinionRequest {

    @NotNull
    private Long userId;
    @NotBlank
    private String opinion;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
