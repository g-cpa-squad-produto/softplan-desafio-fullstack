package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessOpinionVO implements Serializable {

    private int processNumber;

    private String processOpinion;

    private String userName;

/*    public ProcessOpinionVO(final int processNumber, final String processOpinion, final String userName) {
        this.processNumber = processNumber;
        this.processOpinion = processOpinion;
        this.userName = userName;
    }*/
}
