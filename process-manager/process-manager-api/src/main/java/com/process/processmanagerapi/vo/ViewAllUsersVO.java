package com.process.processmanagerapi.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ViewAllUsersVO implements Serializable {

    @NotNull
    private String viewBy;
}
