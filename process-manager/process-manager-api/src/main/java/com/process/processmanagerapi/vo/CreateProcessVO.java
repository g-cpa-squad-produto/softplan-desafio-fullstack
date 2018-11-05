package com.process.processmanagerapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateProcessVO implements Serializable {

    private int processNumber;

    private String processDescription;

    private String createBy;
}
