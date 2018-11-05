package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EditUserVO implements Serializable {

    private String userName;

    private String password;

    private String userType;

    private String editedByUser;
}
