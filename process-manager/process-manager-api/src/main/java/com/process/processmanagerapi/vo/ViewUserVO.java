package com.process.processmanagerapi.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ViewUserVO implements Serializable {

    @NotNull
    private String userName;

    @NotNull
    private String viewBy;
}
