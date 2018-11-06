package com.process.processmanagerapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateProcessVO implements Serializable {

    @NotNull
    private int processNumber;

    @NotNull
    private String processDescription;

    @NotNull
    private String createBy;
}
