package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FinishProcessVO implements Serializable {

    private int processNumber;

    private String finishBy;


}
