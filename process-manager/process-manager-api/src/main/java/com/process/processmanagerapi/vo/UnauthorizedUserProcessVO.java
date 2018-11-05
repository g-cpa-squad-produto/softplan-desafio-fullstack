package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UnauthorizedUserProcessVO implements Serializable {

    private int processNumber;

    private String userName;

    private String authorizedBy;
}
