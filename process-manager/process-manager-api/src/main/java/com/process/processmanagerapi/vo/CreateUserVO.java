package com.process.processmanagerapi.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateUserVO implements Serializable {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String userType;

    @NotNull
    private String createdByUser;
}
