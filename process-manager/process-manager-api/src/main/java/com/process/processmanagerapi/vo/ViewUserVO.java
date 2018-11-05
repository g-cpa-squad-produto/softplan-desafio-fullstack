package com.process.processmanagerapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewUserVO implements Serializable {

    private String userName;

    private String viewBy;
}
