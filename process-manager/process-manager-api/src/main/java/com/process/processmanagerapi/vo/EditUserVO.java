package com.process.processmanagerapi.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EditUserVO implements Serializable {

    @NotNull
    private String userName;

    private String password;

    private String userType;

    @NotNull
    private String editedByUser;
}
